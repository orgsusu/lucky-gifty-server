package dev.orgsusu.adapterWeb.config

import com.fasterxml.jackson.databind.ObjectMapper
import dev.orgsusu.adapterWeb.handler.ExceptionHandleFilter
import dev.orgsusu.adapterWeb.security.SecurityLogoutAdapter
import dev.orgsusu.application.exception.AuthExceptionDetails
import dev.orgsusu.common.exception.ExceptionDetail
import dev.orgsusu.common.response.ResponseError
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.cors.CorsConfigurationSource
import java.io.IOException

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val objectMapper: ObjectMapper,
) {

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        exceptionHandleFilter: ExceptionHandleFilter,
        securityLogoutAdapter: SecurityLogoutAdapter,
    ): SecurityFilterChain {
        return http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .cors { it.configurationSource(corsConfigurationSource()) }
            .sessionManagement {
                it
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .sessionFixation { session -> session.changeSessionId() }
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false)
            }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/auth/logout").authenticated()
                    .requestMatchers("/auth/**").anonymous()
                    .anyRequest().authenticated()
            }
            .exceptionHandling {
                it
                    .accessDeniedHandler { _, res, _ ->
                        res.send(AuthExceptionDetails.UNAUTHORIZED)
                    }
                    .authenticationEntryPoint { req, res, _ ->
                        res.send(AuthExceptionDetails.UNAUTHORIZED)
                    }
            }
            .logout {
                it
                    .logoutUrl("/auth/logout")
                    .invalidateHttpSession(true)
                    .addLogoutHandler(securityLogoutAdapter)
                    .logoutSuccessHandler(securityLogoutAdapter)
            }
            .addFilterBefore(exceptionHandleFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    private fun HttpServletResponse.send(code: ExceptionDetail) {
        val entity = ResponseError.ofRaw(code)
        try {
            status = entity.status
            contentType = MediaType.APPLICATION_JSON_VALUE
            characterEncoding = "UTF-8"
            writer.write(objectMapper.writeValueAsString(entity))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager =
        authenticationConfiguration.authenticationManager

    @Bean
    fun passwordEncoder(): PasswordEncoder =
        BCryptPasswordEncoder()

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration().apply {
            addAllowedOriginPattern("*")
            addAllowedHeader("*")
            addAllowedMethod("*")
            allowCredentials = true
        }
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}

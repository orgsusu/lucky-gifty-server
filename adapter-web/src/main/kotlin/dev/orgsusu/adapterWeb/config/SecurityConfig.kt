package dev.orgsusu.adapterWeb.config

import dev.orgsusu.adapterWeb.handler.ExceptionHandleFilter
import dev.orgsusu.adapterWeb.security.SecurityLogoutAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authenticationConfiguration: AuthenticationConfiguration
) {

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        exceptionHandleFilter: ExceptionHandleFilter,
        securityLogoutAdapter: SecurityLogoutAdapter
    ): SecurityFilterChain {
        return http
            .httpBasic { it.disable() }
            .csrf { it.disable() }
            .cors { it.configurationSource(corsConfigurationSource()) }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .sessionFixation { session -> session.newSession() }
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false)
            }
            .authorizeHttpRequests {
                it.requestMatchers("/auth/logout").authenticated()
                    .requestMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated()
            }
            .logout {
                it.logoutUrl("/auth/logout")
                    .invalidateHttpSession(true)
                    .addLogoutHandler(securityLogoutAdapter)
                    .logoutSuccessHandler(securityLogoutAdapter)
            }
            .addFilterBefore(exceptionHandleFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()
    }

    @Bean
    fun authenticationManager(): AuthenticationManager =
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

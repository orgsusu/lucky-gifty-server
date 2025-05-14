package dev.orgsusu.adapterWeb.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession
import org.springframework.session.web.http.CookieHttpSessionIdResolver
import org.springframework.session.web.http.HttpSessionIdResolver

@Configuration
@EnableSpringHttpSession
class SessionConfig {
    @Bean
    fun securityContextRepository() = HttpSessionSecurityContextRepository()

    @Bean
    fun httpSessionIdResolver(): HttpSessionIdResolver = CookieHttpSessionIdResolver()
}

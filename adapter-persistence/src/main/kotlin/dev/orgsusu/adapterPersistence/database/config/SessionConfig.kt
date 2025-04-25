package dev.orgsusu.adapterPersistence.database.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.session.SessionRegistry
import org.springframework.security.web.context.HttpSessionSecurityContextRepository
import org.springframework.session.data.redis.RedisIndexedSessionRepository
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisIndexedHttpSession
import org.springframework.session.security.SpringSessionBackedSessionRegistry
import org.springframework.session.web.http.HeaderHttpSessionIdResolver
import org.springframework.session.web.http.HttpSessionIdResolver

@Configuration
@EnableRedisIndexedHttpSession
class SessionConfig {
    @Bean
    fun securityContextRepository() = HttpSessionSecurityContextRepository()

    @Bean
    fun sessionRegistry(
        redisIndexedSessionRepository: RedisIndexedSessionRepository,
    ): SessionRegistry = SpringSessionBackedSessionRegistry(redisIndexedSessionRepository)

    @Bean
    fun httpSessionIdResolver(): HttpSessionIdResolver = HeaderHttpSessionIdResolver.xAuthToken()
}

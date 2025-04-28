package dev.orgsusu.adapterPersistence.database.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.security.core.session.SessionRegistry
import org.springframework.security.jackson2.SecurityJackson2Modules
import org.springframework.security.web.authentication.session.ChangeSessionIdAuthenticationStrategy
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy
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

    @Bean
    fun springSessionDefaultRedisSerializer(): RedisSerializer<Any> {
        val objectMapper = ObjectMapper()
            .registerModule(KotlinModule.Builder().build())
            .registerModule(JavaTimeModule())
            .registerModules(SecurityJackson2Modules.getModules(ClassLoader.getSystemClassLoader()))
        return GenericJackson2JsonRedisSerializer(objectMapper)
    }

    @Bean
    fun sessionAuthenticationStrategy(): SessionAuthenticationStrategy {
        return ChangeSessionIdAuthenticationStrategy()
    }
}

package dev.orgsusu.adapterPersistence.database.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.session.SessionRegistry
import org.springframework.session.data.redis.RedisIndexedSessionRepository
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisIndexedHttpSession
import org.springframework.session.security.SpringSessionBackedSessionRegistry

@Configuration
@EnableRedisIndexedHttpSession
class RedisSessionConfig {
    @Bean
    fun sessionRegistry(
        redisIndexedSessionRepository: RedisIndexedSessionRepository,
    ): SessionRegistry = SpringSessionBackedSessionRegistry(redisIndexedSessionRepository)
}

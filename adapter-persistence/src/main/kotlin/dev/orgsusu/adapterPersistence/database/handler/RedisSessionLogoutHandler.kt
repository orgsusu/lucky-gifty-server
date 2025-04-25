package dev.orgsusu.adapterPersistence.database.handler

import dev.orgsusu.domain.handler.SessionLogoutHandler
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.session.data.redis.RedisIndexedSessionRepository
import org.springframework.stereotype.Component

@Component
class RedisSessionLogoutHandlerImpl(
    private val redisIndexedSessionRepository: RedisIndexedSessionRepository,
) : SessionLogoutHandler {
    override fun logoutSession(sessionId: String?) {
        sessionId?.let { redisIndexedSessionRepository.findById(it) }?.let {
            redisIndexedSessionRepository.deleteById(it.id)
        }
    }

    override fun clearSecurityContext() {
        SecurityContextHolder.clearContext()
    }
}

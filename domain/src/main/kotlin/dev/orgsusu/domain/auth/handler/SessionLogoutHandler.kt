package dev.orgsusu.domain.auth.handler

interface SessionLogoutHandler {
    fun logoutSession(sessionId: String?)
    fun clearSecurityContext()
}

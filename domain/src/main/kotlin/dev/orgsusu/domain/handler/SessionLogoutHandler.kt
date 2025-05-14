package dev.orgsusu.domain.handler

interface SessionLogoutHandler {
    fun logoutSession(sessionId: String?)
    fun clearSecurityContext()
}
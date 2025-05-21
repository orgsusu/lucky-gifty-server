package dev.orgsusu.adapterWeb.global.security

import dev.orgsusu.domain.auth.handler.SessionLogoutHandler
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutHandler
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler
import org.springframework.stereotype.Component

@Component
class SecurityLogoutAdapter(
    private val sessionLogoutHandler: SessionLogoutHandler
) : LogoutHandler, LogoutSuccessHandler {
    override fun logout(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication?) {
        val sessionId = request.getSession(false)?.id
        sessionLogoutHandler.logoutSession(sessionId)
    }

    override fun onLogoutSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication?,
    ) {
        sessionLogoutHandler.clearSecurityContext()
    }
}

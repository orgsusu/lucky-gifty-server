package dev.orgsusu.adapterWeb.global.security

import dev.orgsusu.application.domain.user.service.UserContextHolder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityContextUserHolder : UserContextHolder {
    override fun getCurrentUserId(): Long? {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication?.principal is UserDetailsImpl) {
            return (authentication.principal as UserDetailsImpl).id
        }
        return null
    }
}

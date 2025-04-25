package dev.orgsusu.adapterWeb.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import dev.orgsusu.application.service.UserContextHolder

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
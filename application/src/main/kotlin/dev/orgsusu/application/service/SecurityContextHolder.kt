package dev.orgsusu.application.service

import org.springframework.stereotype.Component

@Component
class SecurityContextHolder : UserContextHolder {
    override fun getCurrentUserId(): Long? {
        return null
    }
}
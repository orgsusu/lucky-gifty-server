package dev.orgsusu.application.domain.user.service

interface UserContextHolder {
    fun getCurrentUserId(): Long?
}

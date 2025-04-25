package dev.orgsusu.application.service

interface UserContextHolder {
    fun getCurrentUserId(): Long?
}
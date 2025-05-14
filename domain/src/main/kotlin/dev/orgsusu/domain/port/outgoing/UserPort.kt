package dev.orgsusu.domain.port.outgoing

import dev.orgsusu.domain.model.user.UserDomain

interface UserPort {
    fun save(user: UserDomain): UserDomain
    fun findById(id: Long): UserDomain?
    fun findByCredential(credential: String): UserDomain?
    fun existsByCredential(credential: String): Boolean
}
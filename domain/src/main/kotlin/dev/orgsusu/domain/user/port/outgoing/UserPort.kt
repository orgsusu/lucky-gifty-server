package dev.orgsusu.domain.user.port.outgoing

import dev.orgsusu.domain.user.model.UserDomain

interface UserPort {
    fun save(user: UserDomain): UserDomain
    fun findById(id: Long): UserDomain?
    fun findByCredential(credential: String): UserDomain?
    fun existsByCredential(credential: String): Boolean
}

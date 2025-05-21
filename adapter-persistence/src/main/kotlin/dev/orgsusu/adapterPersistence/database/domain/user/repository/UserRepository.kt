package dev.orgsusu.adapterPersistence.database.domain.user.repository

import dev.orgsusu.adapterPersistence.database.domain.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByCredential(credential: String): UserEntity?
    fun existsByCredential(credential: String): Boolean
}

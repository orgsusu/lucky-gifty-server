package dev.orgsusu.domain.user.model

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

data class UserDomain(
    val id: Long = 0,
    val credential: String,
    val password: String,
    val phone: String?,
    val email: String?,
    val birthDay: LocalDate? = null,
    val status: UserStatus = UserStatus.ACTIVE,
    val deletedAt: LocalDateTime? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
): Serializable

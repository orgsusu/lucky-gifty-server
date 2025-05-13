package dev.orgsusu.domain.model.user

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

data class UserDomain(
    val id: Long = 0,
    val credential: String,
    val password: String,
    val phoneNum: String,
    val mail: String,
    val birthDate: LocalDate? = null,
    val status: UserStatus = UserStatus.ACTIVE,
    val deletedAt: LocalDateTime? = null,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
): Serializable

package dev.orgsusu.domain.luckybox.model

import dev.orgsusu.domain.user.model.UserDomain
import java.time.LocalDateTime
import java.util.UUID

data class LuckyBoxSimpleDomain(
    val id: UUID,
    val name: String,
    val createdBy: UserDomain,
    val resultExists: Boolean,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)

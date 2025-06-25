package dev.orgsusu.domain.luckybox.model

import dev.orgsusu.domain.user.model.UserDomain
import java.time.LocalDateTime
import java.util.UUID

data class LuckyBoxDomain(
    val id: UUID,
    val name: String,
    val createdBy: UserDomain,
    val result: LuckyBoxProductDomain?,
    val products: List<LuckyBoxProductDomain>,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
)

package dev.orgsusu.adapterWeb.domain.luckybox.dto.response

import dev.orgsusu.adapterWeb.domain.user.dto.response.UserResponseDto
import dev.orgsusu.domain.luckybox.model.LuckyBoxDomain
import dev.orgsusu.domain.luckybox.model.LuckyBoxProductDomain
import io.mcarle.konvert.api.KonvertFrom
import java.time.LocalDateTime
import java.util.UUID

@KonvertFrom(LuckyBoxDomain::class)
data class LuckyBoxResponseDto(
    val id: UUID,
    val name: String,
    val createdBy: UserResponseDto,
    val result: LuckyBoxProductDomain?,
    val products: List<LuckyBoxProductDomain>,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) { companion object; }

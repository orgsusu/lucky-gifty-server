package dev.orgsusu.adapterWeb.domain.luckybox.dto.response

import dev.orgsusu.adapterWeb.domain.user.dto.response.UserResponseDto
import dev.orgsusu.domain.luckybox.model.LuckyBoxSimpleDomain
import io.mcarle.konvert.api.KonvertFrom
import java.time.LocalDateTime
import java.util.UUID

@KonvertFrom(LuckyBoxSimpleDomain::class)
data class LuckyBoxSimpleResponseDto(
    val id: UUID,
    val name: String,
    val createdBy: UserResponseDto,
    val resultExists: Boolean,

    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) { companion object; }

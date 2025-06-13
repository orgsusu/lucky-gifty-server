package dev.orgsusu.adaptertoss.domain.dto.response

import dev.orgsusu.adaptertoss.domain.dto.TossCertPersonalData

data class TossCertResultSuccessResponseDto(
    val txId: String,
    val status: String,
    val userIdentifier: String?,
    val userCiToken: String?,
    val signature: String,
    val randomValue: String?,
    val completedDt: String,
    val requestedDt: String,
    val personalData: TossCertPersonalData
)
package dev.orgsusu.adaptertoss.domain.dto.request

data class TossCertTokenRequestDto(
    val clientId: String,
    val clientSecret: String,
    val scope: String,
    val grantType: String
)

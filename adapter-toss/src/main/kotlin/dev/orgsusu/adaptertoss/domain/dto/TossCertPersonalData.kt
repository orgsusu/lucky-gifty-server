package dev.orgsusu.adaptertoss.domain.dto

data class TossCertPersonalData(
    val ci: String,
    val name: String,
    val birthday: String,
    val gender: String,
    val nationality: String,
    val ci2: String?,
    val di: String,
    val ciUpdate: String?
)
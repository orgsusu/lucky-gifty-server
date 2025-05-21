package dev.orgsusu.adapterWeb.domain.auth.dto.request

import jakarta.validation.constraints.NotBlank

data class LoginRequestDto(
    @field:NotBlank
    val credential: String,
    @field:NotBlank
    val password: String
)

package dev.orgsusu.adapterWeb.controller.dto.request

import jakarta.validation.constraints.NotBlank

data class LoginRequestDto(
    @field:NotBlank
    val credential: String,
    @field:NotBlank
    val password: String
)
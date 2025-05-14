package dev.orgsusu.adapterWeb.controller.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import dev.orgsusu.domain.model.user.PartialUserDomain
import io.mcarle.konvert.api.KonvertTo
import java.time.LocalDate

@KonvertTo(PartialUserDomain::class)
data class RegisterRequestDto(
    @field:NotBlank
    val credential: String,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val phoneNum: String,

    @field:NotBlank
    @field:Email
    val mail: String,

    val birthDate: LocalDate? = null
)

package dev.orgsusu.adapterWeb.domain.user.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import dev.orgsusu.domain.user.model.PartialUserDomain
import io.mcarle.konvert.api.KonvertTo
import java.time.LocalDate

@KonvertTo(PartialUserDomain::class)
data class CreateUserRequestDto(
    @field:NotBlank
    val credential: String,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val phone: String,

    @field:NotBlank
    @field:Email
    val mail: String,

    val birthDay: LocalDate? = null
)

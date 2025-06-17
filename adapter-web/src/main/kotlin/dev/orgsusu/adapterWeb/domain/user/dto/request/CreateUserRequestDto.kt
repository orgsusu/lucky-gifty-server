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

    val phone: String?,

    @field:Email
    val email: String?,

    val birthDay: LocalDate? = null
)

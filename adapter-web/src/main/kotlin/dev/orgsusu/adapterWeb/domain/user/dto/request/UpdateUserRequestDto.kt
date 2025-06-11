package dev.orgsusu.adapterWeb.domain.user.dto.request

import jakarta.validation.constraints.Email
import java.time.LocalDate

data class UpdateUserRequestDto(
    val phone: String? = null,

    @field:Email
    val mail: String? = null,

    val birthDay: LocalDate? = null
)

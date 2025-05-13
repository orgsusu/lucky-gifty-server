package dev.orgsusu.adapterWeb.controller.dto.request

import jakarta.validation.constraints.Email
import java.time.LocalDate

data class UpdateUserRequestDto(
    val phoneNum: String? = null,

    @field:Email
    val mail: String? = null,

    val birthDate: LocalDate? = null
)
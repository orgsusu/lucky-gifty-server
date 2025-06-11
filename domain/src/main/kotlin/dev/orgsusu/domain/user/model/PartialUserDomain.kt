package dev.orgsusu.domain.user.model

import java.time.LocalDate

data class PartialUserDomain(
    val credential: String,
    val password: String,
    val phone: String,
    val mail: String,
    val birthDay: LocalDate? = null
)

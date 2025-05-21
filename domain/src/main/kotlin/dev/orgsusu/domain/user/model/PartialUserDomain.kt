package dev.orgsusu.domain.user.model

import java.time.LocalDate

data class PartialUserDomain(
    val credential: String,
    val password: String,
    val phoneNum: String,
    val mail: String,
    val birthDate: LocalDate? = null
)

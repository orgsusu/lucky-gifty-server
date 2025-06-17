package dev.orgsusu.adapterWeb.domain.user.dto.response

import dev.orgsusu.domain.user.model.UserDomain
import dev.orgsusu.domain.user.model.UserStatus
import io.mcarle.konvert.api.KonvertFrom
import java.time.LocalDate

@KonvertFrom(UserDomain::class)
data class UserResponseDto(
    val id: Long,
    val credential: String,
    val phone: String?,
    val email: String?,
    val birthDay: LocalDate? = null,
    val status: UserStatus
) { companion object; }

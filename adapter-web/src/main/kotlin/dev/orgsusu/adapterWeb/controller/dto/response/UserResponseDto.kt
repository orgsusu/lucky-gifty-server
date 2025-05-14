package dev.orgsusu.adapterWeb.controller.dto.response

import dev.orgsusu.domain.model.user.UserDomain
import dev.orgsusu.domain.model.user.UserStatus
import java.time.LocalDate

data class UserResponseDto(
    val id: Long,
    val credential: String,
    val phoneNum: String,
    val mail: String,
    val birthDate: LocalDate? = null,
    val status: UserStatus
) {
    companion object {
        fun fromDomain(user: UserDomain): UserResponseDto {
            return UserResponseDto(
                id = user.id,
                credential = user.credential,
                phoneNum = user.phoneNum,
                mail = user.mail,
                birthDate = user.birthDate,
                status = user.status
            )
        }
    }
}
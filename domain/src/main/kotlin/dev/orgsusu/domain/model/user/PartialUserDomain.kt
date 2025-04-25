package dev.orgsusu.domain.model.user

import java.time.LocalDate

data class PartialUserDomain(
    val credential: String,
    val password: String,
    val phoneNum: String,
    val mail: String,
    val birthDate: LocalDate? = null
) {
    fun toUser(): UserDomain {
        return UserDomain(
            credential = credential,
            password = password,
            phoneNum = phoneNum,
            mail = mail,
            birthDate = birthDate
        )
    }
}

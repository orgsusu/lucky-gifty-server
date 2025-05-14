package dev.orgsusu.domain.port.incoming

import dev.orgsusu.domain.model.user.PartialUserDomain
import dev.orgsusu.domain.model.user.UserDomain
import java.time.LocalDate

interface UserUseCase {
    fun registerUser(partialUser: PartialUserDomain): UserDomain
    fun getUserInfo(id: Long): UserDomain
    fun updateUserInfo(
        id: Long,
        phoneNum: String? = null,
        mail: String? = null,
        birthDate: LocalDate? = null
    ): UserDomain

    fun deleteUser(id: Long): UserDomain
    fun checkCredentialAvailable(credential: String): Boolean
    fun getCurrentUser(): UserDomain
}

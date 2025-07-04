package dev.orgsusu.domain.user.port.incoming

import dev.orgsusu.domain.user.model.PartialUserDomain
import dev.orgsusu.domain.user.model.UserDomain
import java.time.LocalDate

interface UserUseCase {
    fun registerUser(partialUser: PartialUserDomain): UserDomain
    fun getUserInfo(id: Long): UserDomain
    fun updateUserInfo(
        id: Long,
        phone: String? = null,
        email: String? = null,
        birthDay: LocalDate? = null
    ): UserDomain

    fun deleteUser(id: Long): UserDomain
    fun checkCredentialAvailable(credential: String): Boolean
    fun getCurrentUser(): UserDomain
    fun findUserByIdOrThrow(id: Long): UserDomain
}

package dev.orgsusu.application.domain.user.service

import dev.orgsusu.application.domain.user.mapper.UserDomainMapper
import dev.orgsusu.common.exception.CustomException
import dev.orgsusu.application.domain.user.exception.UserExceptionDetails
import dev.orgsusu.domain.user.model.PartialUserDomain
import dev.orgsusu.domain.user.model.UserDomain
import dev.orgsusu.domain.user.model.UserStatus
import dev.orgsusu.domain.user.port.outgoing.UserPort
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class UserServiceImpl(
    private val userPort: UserPort,
    private val passwordEncoder: PasswordEncoder,
    private val userContextHolder: UserContextHolder,
    private val userDomainMapper: UserDomainMapper,
) : UserService {
    override fun registerUser(partialUser: PartialUserDomain): UserDomain {
        checkCredentialAvailable(partialUser.credential)

        val passwordEncodedPartialUser = partialUser.copy(
            password = passwordEncoder.encode(partialUser.password)
        )
        val fullUserDomain = userDomainMapper.mapToFullDomain(
            passwordEncodedPartialUser
        )

        return userPort.save(fullUserDomain)
    }

    override fun getUserInfo(id: Long): UserDomain {
        return findUserByIdOrThrow(id)
    }

    override fun updateUserInfo(
        id: Long,
        phoneNum: String?,
        mail: String?,
        birthDate: LocalDate?
    ): UserDomain {
        val user = findUserByIdOrThrow(id)

        val updatedUser = user.copy(
            phoneNum = phoneNum ?: user.phoneNum,
            mail = mail ?: user.mail,
            birthDate = birthDate ?: user.birthDate
        )

        return userPort.save(updatedUser)
    }

    override fun deleteUser(id: Long): UserDomain {
        val user = findUserByIdOrThrow(id)
        val deletedUser = user.copy(
            status = UserStatus.DELETED,
            deletedAt = LocalDateTime.now()
        )

        return userPort.save(deletedUser)
    }

    override fun checkCredentialAvailable(credential: String): Boolean {
        if (userPort.existsByCredential(credential)) {
            throw CustomException(UserExceptionDetails.USER_ALREADY_EXISTS, credential)
        }
        return userPort.existsByCredential(credential)
    }

    override fun getCurrentUser(): UserDomain {
        val userId = userContextHolder.getCurrentUserId()
            ?: throw CustomException(UserExceptionDetails.AUTHENTICATION_FAILED)

        return getUserInfo(userId)
    }

    override fun findUserByIdOrThrow(id: Long): UserDomain {
        return userPort.findById(id) ?: throw CustomException(UserExceptionDetails.SESSION_USER_NOT_FOUND)
    }
}

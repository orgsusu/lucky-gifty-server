package dev.orgsusu.application.service

import dev.orgsusu.common.exception.CustomException
import dev.orgsusu.application.exception.UserExceptionDetails
import dev.orgsusu.domain.model.user.PartialUserDomain
import dev.orgsusu.domain.model.user.UserDomain
import dev.orgsusu.domain.port.outgoing.UserPort
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UserServiceImpl(
    private val userPort: UserPort,
    private val passwordEncoder: PasswordEncoder,
    private val userContextHolder: UserContextHolder
) : UserService {
    override fun registerUser(partialUser: PartialUserDomain): UserDomain {
        isCredentialAvailable(partialUser.credential)

        val userWithEncodedPassword = partialUser.copy(
            password = passwordEncoder.encode(partialUser.password)
        ).toUser()

        return userPort.save(userWithEncodedPassword)    }

    override fun authenticateUser(credential: String, password: String): UserDomain {
        val user = userPort.findByCredential(credential) ?: throw CustomException(UserExceptionDetails.BAD_CREDENTIALS)

        if (!passwordEncoder.matches(password, user.password)) {
            throw CustomException(UserExceptionDetails.BAD_CREDENTIALS)
        }

        return user
    }

    override fun getUserInfo(id: Long): UserDomain {
        return findUserById(id)
    }

    override fun updateUserInfo(id: Long, phoneNum: String?, mail: String?, birthDate: LocalDate?): UserDomain {
        val user = findUserById(id)
        val updatedUser = user.update(phoneNum, mail, birthDate)
        return userPort.save(updatedUser)
    }

    override fun deleteUser(id: Long): UserDomain {
        val user = findUserById(id)
        val deletedUser = user.delete()
        return userPort.save(deletedUser)
    }

    override fun isCredentialAvailable(credential: String): Boolean {
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

    private fun findUserById(id: Long): UserDomain {
        return userPort.findById(id) ?: throw CustomException(UserExceptionDetails.SESSION_USER_NOT_FOUND)
    }
}
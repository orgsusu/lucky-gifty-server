package dev.orgsusu.adapterWeb.security

import dev.orgsusu.common.exception.CustomException
import dev.orgsusu.application.exception.UserExceptionDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import dev.orgsusu.domain.port.outgoing.UserPort

@Service
class UserDetailsServiceImpl(private val userPort: UserPort) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userPort.findByCredential(username)
            ?: throw CustomException(UserExceptionDetails.SESSION_USER_NOT_FOUND)
        return UserDetailsImpl(user)
    }
}
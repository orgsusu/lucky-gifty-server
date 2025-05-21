package dev.orgsusu.adapterWeb.global.security

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import dev.orgsusu.domain.user.port.outgoing.UserPort
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Service
class UserDetailsServiceImpl(private val userPort: UserPort) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userPort.findByCredential(username)
            ?: throw UsernameNotFoundException("User with credential $username not found")
        return UserDetailsImpl(user)
    }
}

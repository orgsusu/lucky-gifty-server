package dev.orgsusu.adapterWeb.global.security

import dev.orgsusu.domain.user.model.UserDomain
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(val user: UserDomain) : UserDetails {
    val id: Long
        get() = user.id

    override fun getAuthorities(): Collection<GrantedAuthority> = emptyList()

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.credential

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}

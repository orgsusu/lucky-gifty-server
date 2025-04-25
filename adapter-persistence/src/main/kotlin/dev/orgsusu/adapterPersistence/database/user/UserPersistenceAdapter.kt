package dev.orgsusu.adapterPersistence.database.user

import dev.orgsusu.adapterPersistence.database.user.repository.UserRepository
import dev.orgsusu.domain.model.user.UserDomain
import org.springframework.stereotype.Component
import dev.orgsusu.domain.port.outgoing.UserPort

@Component
class UserPersistenceAdapter(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : UserPort {

    override fun save(user: UserDomain): UserDomain {
        val entity = userMapper.toEntity(user)
        val savedEntity = userRepository.save(entity)
        return userMapper.userModelToDomain(savedEntity)
    }

    override fun findById(id: Long): UserDomain? {
        return userRepository.findById(id)
            .map(userMapper::userModelToDomain)
            .orElse(null)
    }

    override fun findByCredential(credential: String): UserDomain? {
        return userRepository.findByCredential(credential)
            ?.let(userMapper::userModelToDomain)
    }

    override fun existsByCredential(credential: String): Boolean {
        return userRepository.existsByCredential(credential)
    }
}
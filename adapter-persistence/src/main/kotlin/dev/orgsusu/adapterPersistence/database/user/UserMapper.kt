package dev.orgsusu.adapterPersistence.database.user

import dev.orgsusu.adapterPersistence.database.user.entity.UserEntity
import io.mcarle.konvert.api.Konverter
import dev.orgsusu.domain.user.model.PartialUserDomain
import dev.orgsusu.domain.user.model.UserDomain
import io.mcarle.konvert.injector.spring.KComponent

@Konverter
@KComponent
interface UserMapper {
    fun toEntity(model: UserDomain): UserEntity
    fun toEntity(model: PartialUserDomain): UserEntity
    fun partialUserModelToDomain(entity: UserEntity): PartialUserDomain
    fun userModelToDomain(entity: UserEntity): UserDomain
}
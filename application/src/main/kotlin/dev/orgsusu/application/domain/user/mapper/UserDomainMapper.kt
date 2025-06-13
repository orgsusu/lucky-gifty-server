package dev.orgsusu.application.domain.user.mapper

import dev.orgsusu.domain.user.model.PartialUserDomain
import dev.orgsusu.domain.user.model.UserDomain
import io.mcarle.konvert.api.Konverter
import io.mcarle.konvert.injector.spring.KComponent

@Konverter
@KComponent
interface UserDomainMapper {
    fun mapToFullDomain(partialUserDomain: PartialUserDomain): UserDomain
}

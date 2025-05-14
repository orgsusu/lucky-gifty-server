package dev.orgsusu.application.dto

import dev.orgsusu.domain.model.user.PartialUserDomain
import dev.orgsusu.domain.model.user.UserDomain
import io.mcarle.konvert.api.Konverter
import io.mcarle.konvert.injector.spring.KComponent

@Konverter
@KComponent
interface UserDomainMapper {
    fun mapToFullDomain(partialUserDomain: PartialUserDomain): UserDomain
}

package dev.orgsusu.adaptertoss.domain.mapper

import dev.orgsusu.adaptertoss.domain.dto.TossCertPersonalData
import dev.orgsusu.adaptertoss.domain.dto.response.TossCertTokenResponseDto
import dev.orgsusu.adaptertoss.domain.dto.wrapper.TossCertResultWrapper
import dev.orgsusu.domain.tosscert.model.response.result.TossCertResultSuccessEncryptResponseDomain
import dev.orgsusu.domain.tosscert.model.response.token.TossCertTokenResponseDomain
import dev.orgsusu.domain.tosscert.model.wrapper.TossCertResultResponseDomainWrapper
import io.mcarle.konvert.api.Konvert
import io.mcarle.konvert.api.Konverter
import io.mcarle.konvert.api.Mapping
import io.mcarle.konvert.injector.spring.KComponent

@Konverter
@KComponent
interface TossCertMapper {
    fun dtoToDomain(tossCertTokenResponseDto: TossCertTokenResponseDto?): TossCertTokenResponseDomain?
    fun toDomain(tossCertPersonalData: TossCertPersonalData?): TossCertResultSuccessEncryptResponseDomain?

    @Konvert(
        mappings = [
            Mapping(target = "success", expression = "toDomain(it.success?.personalData)")
        ]
    )
    fun toDomain(tossCertPersonalData: TossCertResultWrapper?): TossCertResultResponseDomainWrapper?
}
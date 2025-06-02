package dev.orgsusu.adaptertoss.domain.mapper

import dev.orgsusu.adaptertoss.domain.dto.response.TossCertTokenResponseDto
import dev.orgsusu.domain.tosscert.model.response.TossCertTokenResponseDomain
import io.mcarle.konvert.api.Konverter
import io.mcarle.konvert.injector.spring.KComponent

@Konverter
@KComponent
interface TossCertMapper {
    fun dtoToDomain(tossCertTokenResponseDto: TossCertTokenResponseDto?): TossCertTokenResponseDomain?
}
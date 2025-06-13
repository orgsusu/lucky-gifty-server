package dev.orgsusu.adaptertoss.domain.dto.wrapper

import dev.orgsusu.adaptertoss.domain.dto.response.TossCertResultSuccessResponseDto
import dev.orgsusu.domain.tosscert.model.response.TossCertErrorResponseDomain

data class TossCertResultWrapper(
    val resultType: String,
    val success: TossCertResultSuccessResponseDto?,
    val error: TossCertErrorResponseDomain?,
)
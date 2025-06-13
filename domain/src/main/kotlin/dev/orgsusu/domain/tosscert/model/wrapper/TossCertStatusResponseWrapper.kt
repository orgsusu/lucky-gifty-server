package dev.orgsusu.domain.tosscert.model.wrapper

import dev.orgsusu.domain.tosscert.model.response.TossCertErrorResponseDomain
import dev.orgsusu.domain.tosscert.model.response.status.TossCertStatusSuccessResponseDomain

data class TossCertStatusResponseWrapper(
    val resultType: String,
    val success: TossCertStatusSuccessResponseDomain?,
    val error: TossCertErrorResponseDomain?
)
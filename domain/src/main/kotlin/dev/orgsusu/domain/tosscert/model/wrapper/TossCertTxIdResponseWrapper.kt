package dev.orgsusu.domain.tosscert.model.wrapper

import dev.orgsusu.domain.tosscert.model.response.TossCertTxIdErrorResponseDomain
import dev.orgsusu.domain.tosscert.model.response.TossCertTxIdSuccessResponseDomain

data class TossCertTxIdResponseWrapper(
    val resultType: String,
    val success: TossCertTxIdSuccessResponseDomain?,
    val error: TossCertTxIdErrorResponseDomain?
)
package dev.orgsusu.domain.tosscert.model.wrapper

import dev.orgsusu.domain.tosscert.model.response.TossCertErrorResponseDomain
import dev.orgsusu.domain.tosscert.model.response.txid.TossCertTxIdSuccessResponseDomain

data class TossCertTxIdResponseWrapper(
    val resultType: String,
    val success: TossCertTxIdSuccessResponseDomain?,
    val error: TossCertErrorResponseDomain?
)
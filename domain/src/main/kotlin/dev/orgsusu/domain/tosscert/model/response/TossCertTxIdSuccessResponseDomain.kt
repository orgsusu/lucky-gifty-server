package dev.orgsusu.domain.tosscert.model.response

data class TossCertTxIdSuccessResponseDomain(
    val txId: String,
    val requestedDt: String,
    val authUrl: String
)

package dev.orgsusu.domain.tosscert.model.response.txid

data class TossCertTxIdSuccessResponseDomain(
    val txId: String,
    val requestedDt: String,
    val authUrl: String
)
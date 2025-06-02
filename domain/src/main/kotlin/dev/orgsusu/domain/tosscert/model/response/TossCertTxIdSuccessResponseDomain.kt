package dev.orgsusu.domain.tosscert.model.response

import java.time.LocalDateTime

data class TossCertTxIdSuccessResponseDomain(
    val txId: String,
    val requestedDt: LocalDateTime,
    val authUrl: String
)

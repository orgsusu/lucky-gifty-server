package dev.orgsusu.domain.tosscert.model.response

data class TossCertTxIdErrorResponseDomain(
    val errorType: Int,
    val errorCode: String,
    val reason: String
)

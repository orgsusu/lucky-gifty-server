package dev.orgsusu.domain.tosscert.model.response

data class TossCertErrorResponseDomain(
    val errorType: Int,
    val errorCode: String,
    val reason: String
)

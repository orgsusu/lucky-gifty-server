package dev.orgsusu.domain.tosscert.model.response.status

data class TossCertStatusSuccessResponseDomain(
    val txId: String,
    val status: String,
    val requestedDt: String
)
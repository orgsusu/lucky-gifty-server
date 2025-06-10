package dev.orgsusu.domain.tosscert.model.response.result

data class TossCertResultSuccessEncryptResponseDomain(
    val name: String,
    val birthday: String,
    val gender: String,
    val ci: String,
    val di: String
)
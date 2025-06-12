package dev.orgsusu.domain.tosscert.model.response.result

data class TossCertResultSuccessEncryptResponseDomain(
    val ci: String,
    val name: String,
    val phone: String,
    val birthday: String,
    val di: String?,
    val email: String?,
)
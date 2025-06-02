package dev.orgsusu.domain.tosscert.model.response

data class TossCertTokenResponseDomain(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int
)
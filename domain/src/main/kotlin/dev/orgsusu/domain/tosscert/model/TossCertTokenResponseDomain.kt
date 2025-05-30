package dev.orgsusu.domain.tosscert.model

data class TossCertTokenResponseDomain(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int
)
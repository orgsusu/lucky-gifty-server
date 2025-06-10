package dev.orgsusu.domain.tosscert.model.response.token

data class TossCertTokenResponseDomain(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int
)
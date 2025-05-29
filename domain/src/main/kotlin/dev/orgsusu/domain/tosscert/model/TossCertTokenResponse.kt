package dev.orgsusu.domain.tosscert.model

data class TossCertTokenResponse(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int
)
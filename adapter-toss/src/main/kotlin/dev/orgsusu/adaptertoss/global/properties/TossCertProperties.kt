package dev.orgsusu.adaptertoss.global.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "toss")
data class TossCertProperties(
    val clientId: String,
    val clientSecret: String,
    val publicKey: String,
    val privateKey: String
)
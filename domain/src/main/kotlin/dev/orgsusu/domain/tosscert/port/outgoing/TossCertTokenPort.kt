package dev.orgsusu.domain.tosscert.port.outgoing

interface TossCertTokenPort {
    fun getToken(): String?
    fun saveToken(token: String, expiresInSeconds: Long)
}
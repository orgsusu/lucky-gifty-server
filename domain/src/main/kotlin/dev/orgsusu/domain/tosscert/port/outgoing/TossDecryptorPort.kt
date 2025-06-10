package dev.orgsusu.domain.tosscert.port.outgoing

interface TossDecryptorPort {
    fun <T : Any> decryptAll(session: String, encrypted: T): T
}
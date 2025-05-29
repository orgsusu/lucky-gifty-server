package dev.orgsusu.domain.tosscert.port.outgoing

import dev.orgsusu.domain.tosscert.model.TossCertTokenResponse

interface TossCertTokenPort {
    fun fetchAccessToken(): TossCertTokenResponse?
}
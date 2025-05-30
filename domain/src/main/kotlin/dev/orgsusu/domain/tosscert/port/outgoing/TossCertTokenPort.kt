package dev.orgsusu.domain.tosscert.port.outgoing

import dev.orgsusu.domain.tosscert.model.TossCertTokenResponseDomain

interface TossCertTokenPort {
    fun fetchAccessToken(): TossCertTokenResponseDomain?
}
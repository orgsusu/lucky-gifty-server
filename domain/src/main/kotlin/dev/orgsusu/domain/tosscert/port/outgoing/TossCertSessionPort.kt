package dev.orgsusu.domain.tosscert.port.outgoing

import dev.orgsusu.domain.tosscert.model.TossCertSessionInfo

interface TossCertSessionPort{
    fun generateSession(): TossCertSessionInfo
}

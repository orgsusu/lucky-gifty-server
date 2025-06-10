package dev.orgsusu.domain.tosscert.port.ingoing

import dev.orgsusu.domain.tosscert.model.TossCertSessionInfo

interface TossCertSessionUseCase{
    fun generateSession(): TossCertSessionInfo
}

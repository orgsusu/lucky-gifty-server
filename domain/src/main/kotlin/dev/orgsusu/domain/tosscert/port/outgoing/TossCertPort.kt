package dev.orgsusu.domain.tosscert.port.outgoing

import dev.orgsusu.domain.tosscert.model.response.TossCertTxIdSuccessResponseDomain
import dev.orgsusu.domain.tosscert.model.response.TossCertTokenResponseDomain

interface TossCertPort {
    fun requestAccessToken(): TossCertTokenResponseDomain?
    fun requestTxId(): TossCertTxIdSuccessResponseDomain?
}
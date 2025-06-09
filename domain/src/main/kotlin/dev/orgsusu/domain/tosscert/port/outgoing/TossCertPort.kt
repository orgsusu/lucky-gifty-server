package dev.orgsusu.domain.tosscert.port.outgoing

import dev.orgsusu.domain.tosscert.model.response.TossCertTokenResponseDomain
import dev.orgsusu.domain.tosscert.model.wrapper.TossCertTxIdResponseWrapper

interface TossCertPort {
    fun requestAccessToken(): TossCertTokenResponseDomain?
    fun requestTxId(accessToken: String): TossCertTxIdResponseWrapper?
}
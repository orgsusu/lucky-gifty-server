package dev.orgsusu.domain.tosscert.port.outgoing

import dev.orgsusu.domain.tosscert.model.response.token.TossCertTokenResponseDomain
import dev.orgsusu.domain.tosscert.model.wrapper.TossCertResultResponseDomainWrapper
import dev.orgsusu.domain.tosscert.model.wrapper.TossCertStatusResponseWrapper
import dev.orgsusu.domain.tosscert.model.wrapper.TossCertTxIdResponseWrapper

interface TossCertPort {
    fun requestAccessToken(): TossCertTokenResponseDomain?
    fun requestTxId(accessToken: String): TossCertTxIdResponseWrapper?
    fun requestStatus(accessToken: String, txId: String): TossCertStatusResponseWrapper?
    fun requestResult(accessToken: String, txId: String, sessionKey: String): TossCertResultResponseDomainWrapper?
}
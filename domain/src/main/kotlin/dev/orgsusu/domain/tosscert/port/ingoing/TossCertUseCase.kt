package dev.orgsusu.domain.tosscert.port.ingoing

import dev.orgsusu.domain.tosscert.model.response.TossCertErrorResponseDomain
import dev.orgsusu.domain.tosscert.model.wrapper.TossCertStatusResponseWrapper
import dev.orgsusu.domain.tosscert.model.wrapper.TossCertTxIdResponseWrapper

interface TossCertUseCase {
    fun getTxId(): TossCertTxIdResponseWrapper
    fun getStatus(txId: String): TossCertStatusResponseWrapper
    fun getResult(txId: String): TossCertErrorResponseDomain?
}
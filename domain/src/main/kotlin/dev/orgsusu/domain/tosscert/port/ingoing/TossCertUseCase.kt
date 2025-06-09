package dev.orgsusu.domain.tosscert.port.ingoing

import dev.orgsusu.domain.tosscert.model.wrapper.TossCertTxIdResponseWrapper

interface TossCertUseCase {
    fun getTxId(): TossCertTxIdResponseWrapper
}
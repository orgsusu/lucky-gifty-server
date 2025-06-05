package dev.orgsusu.domain.tosscert.port.ingoing

import dev.orgsusu.domain.tosscert.model.response.TossCertTxIdSuccessResponseDomain

interface TossCertUseCase {
    fun getTxId(): TossCertTxIdSuccessResponseDomain
}
package dev.orgsusu.application.domain.tosscert.service

import dev.orgsusu.application.domain.tosscert.exception.TossCertExceptionDetails
import dev.orgsusu.common.exception.CustomException
import dev.orgsusu.domain.tosscert.model.response.TossCertTokenResponseDomain
import dev.orgsusu.domain.tosscert.model.response.TossCertTxIdSuccessResponseDomain
import dev.orgsusu.domain.tosscert.port.outgoing.TossCertPort
import org.springframework.stereotype.Service

@Service
class TossCertServiceImpl(
    private val tossCertPort: TossCertPort
) : TossCertService {
    override fun getAccessToken(): TossCertTokenResponseDomain {
        return tossCertPort.requestAccessToken() ?: throw CustomException(TossCertExceptionDetails.FAIL_TO_FETCH)
    }

    override fun getTxId(): TossCertTxIdSuccessResponseDomain {
        return tossCertPort.requestTxId() ?: throw CustomException(TossCertExceptionDetails.FAIL_TO_FETCH)
    }
}
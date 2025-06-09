package dev.orgsusu.application.domain.tosscert.service

import dev.orgsusu.application.domain.tosscert.exception.TossCertExceptionDetails
import dev.orgsusu.common.exception.CustomException
import dev.orgsusu.domain.tosscert.model.wrapper.TossCertTxIdResponseWrapper
import dev.orgsusu.domain.tosscert.port.outgoing.TossCertPort
import dev.orgsusu.domain.tosscert.port.outgoing.TossCertTokenPort
import org.springframework.stereotype.Service

@Service
class TossCertServiceImpl(
    private val tossCertPort: TossCertPort,
    private val tossCertTokenPort: TossCertTokenPort
) : TossCertService {
    fun getAccessToken(): String {
        return tossCertTokenPort.getToken()
            ?: tossCertPort.requestAccessToken()?.let { tokenResponse ->
                tossCertTokenPort.saveToken(tokenResponse.accessToken, tokenResponse.expiresIn.toLong())
                tokenResponse.accessToken
            } ?: throw CustomException(TossCertExceptionDetails.FAIL_TO_FETCH)
    }

    override fun getTxId(): TossCertTxIdResponseWrapper {
        return tossCertPort.requestTxId(getAccessToken())
            ?: throw CustomException(TossCertExceptionDetails.FAIL_TO_FETCH)
    }
}
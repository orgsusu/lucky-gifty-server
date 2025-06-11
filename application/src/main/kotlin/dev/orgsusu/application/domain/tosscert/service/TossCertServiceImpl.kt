package dev.orgsusu.application.domain.tosscert.service

import dev.orgsusu.application.domain.tosscert.exception.TossCertExceptionDetails
import dev.orgsusu.common.exception.CustomException
import dev.orgsusu.domain.tosscert.model.response.result.TossCertResultSuccessEncryptResponseDomain
import dev.orgsusu.domain.tosscert.model.wrapper.TossCertResultResponseDomainWrapper
import dev.orgsusu.domain.tosscert.model.wrapper.TossCertStatusResponseWrapper
import dev.orgsusu.domain.tosscert.model.wrapper.TossCertTxIdResponseWrapper
import dev.orgsusu.domain.tosscert.port.outgoing.TossCertSessionPort
import dev.orgsusu.domain.tosscert.port.outgoing.TossDecryptorPort
import dev.orgsusu.domain.tosscert.port.outgoing.TossCertPort
import dev.orgsusu.domain.tosscert.port.outgoing.TossCertTokenPort
import org.springframework.stereotype.Service

@Service
class TossCertServiceImpl(
    private val tossCertPort: TossCertPort,
    private val tossCertTokenPort: TossCertTokenPort,
    private val tossCertSessionPort: TossCertSessionPort,
    private val tossDecryptorPort: TossDecryptorPort,
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

    override fun getStatus(txId: String): TossCertStatusResponseWrapper {
        val token = getAccessToken()
        return tossCertPort.requestStatus(token, txId)
            ?: throw CustomException(TossCertExceptionDetails.FAIL_TO_FETCH)
    }

    override fun getResult(txId: String): TossCertResultResponseDomainWrapper {
        val token = getAccessToken()
        val session = tossCertSessionPort.generateSession()
        val result = tossCertPort.requestResult(token, txId, session.sessionKey)
            ?: throw CustomException(TossCertExceptionDetails.FAIL_TO_FETCH)
        val decryptedSuccess = result.success?.let {
            tossDecryptorPort.decryptAll<TossCertResultSuccessEncryptResponseDomain>(session.session, it)
        }

        return TossCertResultResponseDomainWrapper(
            resultType = result.resultType,
            success = decryptedSuccess,
            error = result.error
        )
    }
}
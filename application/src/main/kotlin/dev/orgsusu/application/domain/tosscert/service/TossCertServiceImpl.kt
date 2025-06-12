package dev.orgsusu.application.domain.tosscert.service

import dev.orgsusu.application.domain.tosscert.exception.TossCertExceptionDetails
import dev.orgsusu.common.exception.CustomException
import dev.orgsusu.domain.tosscert.model.response.TossCertErrorResponseDomain
import dev.orgsusu.domain.tosscert.model.response.result.TossCertResultSuccessEncryptResponseDomain
import dev.orgsusu.domain.tosscert.model.wrapper.TossCertStatusResponseWrapper
import dev.orgsusu.domain.tosscert.model.wrapper.TossCertTxIdResponseWrapper
import dev.orgsusu.domain.tosscert.port.outgoing.TossCertSessionPort
import dev.orgsusu.domain.tosscert.port.outgoing.TossDecryptorPort
import dev.orgsusu.domain.tosscert.port.outgoing.TossCertPort
import dev.orgsusu.domain.tosscert.port.outgoing.TossCertTokenPort
import dev.orgsusu.domain.user.port.incoming.UserUseCase
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class TossCertServiceImpl(
    private val tossCertPort: TossCertPort,
    private val tossCertTokenPort: TossCertTokenPort,
    private val tossCertSessionPort: TossCertSessionPort,
    private val tossDecryptorPort: TossDecryptorPort,
    private val userUseCase: UserUseCase,
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

    override fun getResult(txId: String): TossCertErrorResponseDomain? {
        val token = getAccessToken()
        val session = tossCertSessionPort.generateSession()
        val result = tossCertPort.requestResult(token, txId, session.sessionKey)
            ?: throw CustomException(TossCertExceptionDetails.FAIL_TO_FETCH)

        val success = result.success
        if (success != null) {
            val decryptedSuccess = tossDecryptorPort.decryptAll<TossCertResultSuccessEncryptResponseDomain>(session.session, success)
            updateUserWithResult(decryptedSuccess)
        }
        return result.error
    }

    private fun updateUserWithResult(decryptedSuccess: TossCertResultSuccessEncryptResponseDomain) {
        userUseCase.updateUserInfo(
            id = userUseCase.getCurrentUser().id,
            phone = decryptedSuccess.phone,
            mail = decryptedSuccess.email,
            birthDay = LocalDate.parse(decryptedSuccess.birthday, DateTimeFormatter.BASIC_ISO_DATE)
        )
    }

}
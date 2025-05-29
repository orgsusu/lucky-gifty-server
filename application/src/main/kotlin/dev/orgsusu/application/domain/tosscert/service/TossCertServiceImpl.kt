package dev.orgsusu.application.domain.tosscert.service

import dev.orgsusu.application.domain.tosscert.exception.TossCertExceptionDetails
import dev.orgsusu.common.exception.CustomException
import dev.orgsusu.domain.tosscert.model.TossCertTokenResponse
import dev.orgsusu.domain.tosscert.port.outgoing.TossCertTokenPort
import org.springframework.stereotype.Service

@Service
class TossCertServiceImpl(
    private val tossCertTokenPort: TossCertTokenPort
) : TossCertService {
    override fun issueAccessToken(): TossCertTokenResponse {
        return tossCertTokenPort.fetchAccessToken() ?: throw CustomException(TossCertExceptionDetails.FAIL_TO_FETCH)
    }
}
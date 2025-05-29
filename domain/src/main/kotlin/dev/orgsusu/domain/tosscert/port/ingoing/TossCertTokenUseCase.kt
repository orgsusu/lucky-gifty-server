package dev.orgsusu.domain.tosscert.port.ingoing

import dev.orgsusu.domain.tosscert.model.TossCertTokenResponse

interface TossCertTokenUseCase {
    fun issueAccessToken(): TossCertTokenResponse
}
package dev.orgsusu.domain.tosscert.port.ingoing

import dev.orgsusu.domain.tosscert.model.TossCertTokenResponseDomain

interface TossCertTokenUseCase {
    fun issueAccessToken(): TossCertTokenResponseDomain
}
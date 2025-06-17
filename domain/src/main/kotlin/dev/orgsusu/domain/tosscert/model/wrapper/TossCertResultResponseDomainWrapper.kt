package dev.orgsusu.domain.tosscert.model.wrapper

import dev.orgsusu.domain.tosscert.model.response.TossCertErrorResponseDomain
import dev.orgsusu.domain.tosscert.model.response.result.TossCertResultSuccessEncryptResponseDomain

data class TossCertResultResponseDomainWrapper(
    val resultType: String,
    val success: TossCertResultSuccessEncryptResponseDomain?,
    val error: TossCertErrorResponseDomain?
)
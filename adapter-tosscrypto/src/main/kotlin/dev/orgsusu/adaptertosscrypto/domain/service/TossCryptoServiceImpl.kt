package dev.orgsusu.adaptertosscrypto.domain.service

import dev.orgsusu.adaptertosscrypto.global.TossSessionConfig
import dev.orgsusu.domain.tosscert.model.TossCertSessionInfo
import dev.orgsusu.domain.tosscert.port.ingoing.TossCertSessionUseCase
import org.springframework.context.annotation.Configuration

@Configuration
class TossCryptoServiceImpl(
    private val tossCertSessionGenerator: TossSessionConfig,
) : TossCertSessionUseCase {

    override fun generateSession(): TossCertSessionInfo {
        val session = tossCertSessionGenerator.tossCertSessionGenerator().generate()
        return TossCertSessionInfo(
            sessionKey = session.sessionKey,
            session = session.serializeSession()
        )
    }
}
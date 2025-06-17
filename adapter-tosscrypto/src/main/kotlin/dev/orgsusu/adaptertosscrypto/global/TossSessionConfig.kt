package dev.orgsusu.adaptertosscrypto.global

import im.toss.cert.sdk.TossCertSessionGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TossSessionConfig {
    @Bean
    fun tossCertSessionGenerator(): TossCertSessionGenerator{
        return TossCertSessionGenerator()
    }
}
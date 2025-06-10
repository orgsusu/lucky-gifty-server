package dev.orgsusu.adaptertoss.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {
    @Bean
    fun tossCertClient(): WebClient =
        WebClient.builder()
            .baseUrl("https://cert.toss.im")
            .build()

    @Bean
    fun tossOauthClient(): WebClient =
        WebClient.builder()
            .baseUrl("https://oauth2.cert.toss.im")
            .build()
}

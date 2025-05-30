package dev.orgsusu.adaptertoss.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {
    @Bean
    fun tossWebClient(): WebClient {
        return WebClient.builder().build()
    }
}

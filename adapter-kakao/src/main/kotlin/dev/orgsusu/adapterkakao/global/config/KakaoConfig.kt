package dev.orgsusu.adapterkakao.global.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class KakaoConfig {
    @Bean
    fun kakaoClient(): WebClient =
        WebClient.builder()
            .baseUrl("https://gift.kakao.com/a")
            .build()
}

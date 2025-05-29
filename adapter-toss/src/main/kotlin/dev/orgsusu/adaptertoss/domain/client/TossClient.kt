package dev.orgsusu.adaptertoss.domain.client

import dev.orgsusu.domain.tosscert.model.TossCertTokenResponse
import dev.orgsusu.domain.tosscert.port.outgoing.TossCertTokenPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

@Component
class TossClient(
    private val webClient: WebClient,
    @Value("\${toss.client-id}") private val clientId: String,
    @Value("\${toss.client-secret}") private val clientSecret: String
) : TossCertTokenPort {

    override fun fetchAccessToken(): TossCertTokenResponse? {
        val formData: MultiValueMap<String, String> = LinkedMultiValueMap<String, String>().apply {
            add("grant_type", "client_credentials")
            add("client_id", clientId)
            add("client_secret", clientSecret)
            add("scope", "ca")
        }

        return webClient.post()
            .uri("https://oauth2.cert.toss.im/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData(formData))
            .retrieve()
            .bodyToMono(TossCertTokenResponse::class.java)
            .block()
    }

}
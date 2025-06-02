package dev.orgsusu.adaptertoss.domain.client

import dev.orgsusu.adaptertoss.domain.dto.TossCertTokenResponseDto
import dev.orgsusu.adaptertoss.domain.mapper.TossCertMapper
import dev.orgsusu.domain.tosscert.model.TossCertTokenResponseDomain
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
    private val tossWebClient: WebClient,
    private val tossCertMapper: TossCertMapper,
    private val tossCertProperties: TossCertProperties
) : TossCertPort {

    override fun requestAccessToken(): TossCertTokenResponseDomain? {

        val getTokenFormData: MultiValueMap<String, String> = LinkedMultiValueMap<String, String>().apply {
            add("grant_type", "client_credentials")
            add("client_id", tossCertProperties.clientId)
            add("client_secret", tossCertProperties.clientSecret)
            add("scope", "ca")
        }

        val request = tossWebClient.post()
            .uri("https://oauth2.cert.toss.im/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData(formData))
            .retrieve()
            .bodyToMono(TossCertTokenResponseDto::class.java)
            .block()

        return tossCertMapper.dtoToDomain(request)
    }

    override fun requestTxId(): TossCertTxIdSuccessResponseDomain? {
        val txIdRequestDto = TossCertTxIdRequestDto(
            requestType = "USER_NONE",
            requestUrl = "https://cert.toss.im"
        )

        val request = tossWebClient.post()
            .uri("https://cert.toss.im/api/v2/sign/user/auth/id/request")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(txIdRequestDto))
            .retrieve()
            .bodyToMono(TossCertTxIdSuccessResponseDomain::class.java)
            .block()

        return request
    }
}
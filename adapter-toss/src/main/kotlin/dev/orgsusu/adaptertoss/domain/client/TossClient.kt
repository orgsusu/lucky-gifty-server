package dev.orgsusu.adaptertoss.domain.client

import dev.orgsusu.adaptertoss.domain.dto.request.TossCertResultRequestDto
import dev.orgsusu.adaptertoss.domain.dto.request.TossCertStatusRequestDto
import dev.orgsusu.adaptertoss.domain.dto.request.TossCertTxIdRequestDto
import dev.orgsusu.adaptertoss.domain.dto.response.TossCertTokenResponseDto
import dev.orgsusu.adaptertoss.domain.mapper.TossCertMapper
import dev.orgsusu.adaptertoss.global.properties.TossCertProperties
import dev.orgsusu.domain.tosscert.model.response.TossCertTokenResponseDomain
import dev.orgsusu.domain.tosscert.model.wrapper.TossCertResultResponseWrapper
import dev.orgsusu.domain.tosscert.model.wrapper.TossCertStatusResponseWrapper
import dev.orgsusu.domain.tosscert.model.wrapper.TossCertTxIdResponseWrapper
import dev.orgsusu.domain.tosscert.port.outgoing.TossCertPort
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

@Component
class TossClient(
    private val tossCertClient: WebClient,
    private val tossOauthClient: WebClient,
    private val tossCertMapper: TossCertMapper,
    private val tossCertProperties: TossCertProperties,
) : TossCertPort {

    override fun requestAccessToken(): TossCertTokenResponseDomain? {
        val getTokenFormData: MultiValueMap<String, String> = LinkedMultiValueMap<String, String>().apply {
            add("grant_type", "client_credentials")
            add("client_id", tossCertProperties.clientId)
            add("client_secret", tossCertProperties.clientSecret)
            add("scope", "ca")
        }

        val request = tossOauthClient.post()
            .uri("/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData(getTokenFormData))
            .retrieve()
            .bodyToMono(TossCertTokenResponseDto::class.java)
            .block()

        return tossCertMapper.dtoToDomain(request)
    }

    override fun requestTxId(accessToken: String): TossCertTxIdResponseWrapper? {
        val txIdRequestDto = TossCertTxIdRequestDto(
            requestType = "USER_NONE",
            requestUrl = "https://cert.toss.im"
        )

        val request = tossCertClient.post()
            .uri("/api/v2/sign/user/auth/id/request")
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer $accessToken")
            .body(BodyInserters.fromValue(txIdRequestDto))
            .retrieve()
            .bodyToMono(TossCertTxIdResponseWrapper::class.java)
            .block()

        return request
    }
}
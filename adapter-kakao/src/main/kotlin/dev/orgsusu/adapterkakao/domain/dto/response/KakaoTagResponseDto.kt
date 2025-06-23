package dev.orgsusu.adapterkakao.domain.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class KakaoTagResponseDto(
    val id: Long,
    @JsonProperty("displayText")
    val name: String,
    @JsonProperty("displayTag")
    val tag: String,
)

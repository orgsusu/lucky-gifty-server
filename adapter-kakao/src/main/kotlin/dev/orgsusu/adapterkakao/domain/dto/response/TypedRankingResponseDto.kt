package dev.orgsusu.adapterkakao.domain.dto.response

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
import dev.orgsusu.adapterkakao.domain.dto.ProductDto

@Suppress("unused")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", include = JsonTypeInfo.As.PROPERTY)
sealed interface TypedRankingResponseDto {
    @JsonTypeName("RANKING_VIEW")
    data class RankingView(
        val product: ProductDto
    ) : TypedRankingResponseDto

    @JsonTypeName("GROUP")
    data object Group : TypedRankingResponseDto

    @JsonTypeName("PRICE_RANGE")
    data object PriceRange : TypedRankingResponseDto
}

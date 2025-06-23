package dev.orgsusu.adapterkakao.domain.dto.response

import dev.orgsusu.adapterkakao.domain.dto.ProductDto

data class TargetedRankingResponseDto(
    val rankRange: TargetedRankingPriceRangeDto,
    val rankingItem: ProductDto,
)

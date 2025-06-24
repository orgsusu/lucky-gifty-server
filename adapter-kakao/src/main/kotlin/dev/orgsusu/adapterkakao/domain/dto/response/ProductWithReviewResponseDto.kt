package dev.orgsusu.adapterkakao.domain.dto.response

import dev.orgsusu.adapterkakao.domain.dto.ProductDto

data class ProductWithReviewResponseDto(
    val rank: Int,
    val displayDate: String,
    val nickname: String,
    val content: String,
    val product: ProductDto,
)

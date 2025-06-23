package dev.orgsusu.adapterkakao.domain.dto.response

import dev.orgsusu.adapterkakao.domain.dto.ProductDto

data class ProductSearchResultResponseDto(
    val totalCount: Int,
    val contents: List<ProductDto>,
    val last: Boolean,
)

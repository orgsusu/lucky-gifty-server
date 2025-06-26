package dev.orgsusu.adapterkakao.domain.dto.response

data class ProductSearchResultResponseDto(
    val totalCount: Int,
    val contents: List<SearchProductResponseDto>,
    val last: Boolean,
)

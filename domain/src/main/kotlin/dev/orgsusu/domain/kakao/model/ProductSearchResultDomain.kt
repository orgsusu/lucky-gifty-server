package dev.orgsusu.domain.kakao.model

data class ProductSearchResultDomain(
    val total: Int,
    val last: Boolean,
    val data: List<ProductDomain>,
)

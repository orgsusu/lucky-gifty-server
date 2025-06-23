package dev.orgsusu.domain.kakao.model

data class ProductPriceDomain(
    val basicPrice: Int,
    val sellingPrice: Int,
    val discountRate: Int,
)

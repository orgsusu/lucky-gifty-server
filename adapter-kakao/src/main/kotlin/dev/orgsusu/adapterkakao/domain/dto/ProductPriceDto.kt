package dev.orgsusu.adapterkakao.domain.dto

data class ProductPriceDto(
    val basicPrice: Int,
    val sellingPrice: Int,
    val discountRate: Int,
)

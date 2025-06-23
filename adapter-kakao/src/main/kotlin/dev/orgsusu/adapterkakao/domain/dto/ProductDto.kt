package dev.orgsusu.adapterkakao.domain.dto

data class ProductDto(
    val id: Long,
    val giftItemId: Long,

    val name: String,
    val imageUrl: String,
    val productType: String,

    val price: ProductPriceDto,
    val brand: ProductBrandDto,
)

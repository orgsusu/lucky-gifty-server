package dev.orgsusu.domain.kakao.model

data class ProductDomain(
    val id: Long,
    val giftItemId: Long,

    val name: String,
    val imageUrl: String,
    val productType: String,

    val price: ProductPriceDomain,
    val brand: ProductBrandDomain,
)

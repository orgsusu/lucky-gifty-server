package dev.orgsusu.domain.kakao.model

data class ProductDomain(
    val id: Long,
    val giftItemId: Long,

    val name: String,
    val imageUrl: String?,

    val basicPrice: Int,
    val sellingPrice: Int,
    val discountRate: Int,

    val brand: ProductBrandDomain,
)

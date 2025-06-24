package dev.orgsusu.adapterkakao.domain.dto.response

data class PartialProductDto(
    val id: Long,
    val giftItemId: Long,

    val name: String,
    val imageUrl: String?,
    val productType: String,

    val basicPrice: Int,
    val sellingPrice: Int,
    val discountRate: Int,
)

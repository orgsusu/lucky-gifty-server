package dev.orgsusu.adapterkakao.domain.dto.response

import dev.orgsusu.adapterkakao.domain.dto.ProductPriceDto

data class PartialProductDto(
    val id: Long,
    val giftItemId: Long,

    val name: String,
    val imageUrl: String?,
    val productType: String,

    val price: ProductPriceDto,
)

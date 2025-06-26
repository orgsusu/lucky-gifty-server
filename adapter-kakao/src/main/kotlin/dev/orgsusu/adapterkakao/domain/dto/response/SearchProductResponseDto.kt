package dev.orgsusu.adapterkakao.domain.dto.response

import dev.orgsusu.adapterkakao.domain.dto.ProductBrandDto
import dev.orgsusu.adapterkakao.domain.dto.ProductPriceDto

data class SearchProductResponseDto(
    val id: Long,
    val giftItemId: Long,

    val name: String,
    val image: SearchProductImageResponseDto,
    val productType: String,

    val price: ProductPriceDto,
    val brand: ProductBrandDto,
)

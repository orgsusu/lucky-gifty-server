package dev.orgsusu.adapterkakao.domain.dto.response

import dev.orgsusu.adapterkakao.domain.dto.ProductBrandDto

data class GiftItemDetailResponseDto(
    val item: PartialProductDto,
    val brand: ProductBrandDto
)

package dev.orgsusu.adapterkakao.domain.dto

import com.fasterxml.jackson.annotation.JsonAlias

data class ProductBrandDto(
    val id: Long,
    val name: String,
    @JsonAlias("imageUrl", "thumbnailUrl")
    val imageUrl: String,
)

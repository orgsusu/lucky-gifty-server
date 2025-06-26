package dev.orgsusu.domain.luckybox.model

import java.util.UUID

data class LuckyBoxProductDomain(
    val luckyBoxId: UUID,
    val id: Long,

    val name: String,
    val imageUrl: String?,
    val price: Int,

    val brandName: String,
)

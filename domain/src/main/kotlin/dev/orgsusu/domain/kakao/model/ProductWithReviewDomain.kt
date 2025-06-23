package dev.orgsusu.domain.kakao.model

import java.time.LocalDateTime

data class ProductWithReviewDomain(
    val rank: Int,
    val createdAt: LocalDateTime,
    val nickname: String,
    val content: String,
    val product: ProductDomain,
)

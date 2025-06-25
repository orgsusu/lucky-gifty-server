package dev.orgsusu.adapterPersistence.database.domain.luckybox.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.UUID

@Embeddable
data class LuckyBoxProductId(
    @Column(name = "lucky_box_id", nullable = false)
    val luckyBoxId: UUID,
    @Column(name = "product_id", nullable = false)
    val productId: Long,
) : Serializable

package dev.orgsusu.adapterPersistence.database.domain.luckybox.entity

import jakarta.persistence.Column
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table

@Entity
@Table(name = "tb_lucky_box_product")
class LuckyBoxProductEntity(
    @EmbeddedId
    val id: LuckyBoxProductId,

    @Column("product_name", nullable = false)
    val name: String,
    @Column("product_image_url")
    val imageUrl: String?,
    @Column("product_price", nullable = false)
    val price: Int,

    @Column("product_brand_name", nullable = false)
    val brandName: String,

    @ManyToOne
    @MapsId("luckyBoxId")
    @JoinColumn(name = "lucky_box_id")
    val luckyBox: LuckyBoxEntity
)

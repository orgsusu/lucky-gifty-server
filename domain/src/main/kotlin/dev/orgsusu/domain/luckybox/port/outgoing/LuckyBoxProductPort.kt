package dev.orgsusu.domain.luckybox.port.outgoing

import dev.orgsusu.domain.luckybox.model.LuckyBoxProductDomain
import java.util.UUID

interface LuckyBoxProductPort {
    fun createProduct(
        uuid: UUID,
        id: Long,

        name: String,
        imageUrl: String?,
        price: Int,
        brandName: String,
    ): LuckyBoxProductDomain?

    fun removeProductById(uuid: UUID, id: Long)
}

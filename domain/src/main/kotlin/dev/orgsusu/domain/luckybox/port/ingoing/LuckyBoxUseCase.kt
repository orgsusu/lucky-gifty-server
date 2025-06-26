package dev.orgsusu.domain.luckybox.port.ingoing

import dev.orgsusu.domain.luckybox.model.LuckyBoxDomain
import dev.orgsusu.domain.luckybox.model.LuckyBoxProductDomain
import dev.orgsusu.domain.luckybox.model.LuckyBoxSimpleDomain
import java.util.UUID

interface LuckyBoxUseCase {
    fun getAllLuckyBoxes(): List<LuckyBoxSimpleDomain>
    fun getLuckyBoxById(uuid: UUID): LuckyBoxDomain

    fun openLuckyBox(uuid: UUID): LuckyBoxProductDomain

    fun createLuckyBox(name: String): LuckyBoxDomain
    fun deleteLuckyBoxById(uuid: UUID)

    fun addProductOnLuckyBox(uuid: UUID, id: Long): LuckyBoxProductDomain
    fun deleteProductOnLuckyBox(uuid: UUID, id: Long)
}

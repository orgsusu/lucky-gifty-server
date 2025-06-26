package dev.orgsusu.domain.luckybox.port.outgoing

import dev.orgsusu.domain.luckybox.model.LuckyBoxDomain
import dev.orgsusu.domain.luckybox.model.LuckyBoxSimpleDomain
import java.util.UUID

interface LuckyBoxPort {
    fun getAllLuckyBoxes(userId: Long): List<LuckyBoxSimpleDomain>
    fun createLuckyBox(userId: Long, name: String): LuckyBoxDomain?
    fun getLuckyBoxById(uuid: UUID): LuckyBoxDomain?
    fun deleteLuckyBoxById(uuid: UUID)

    fun setSelectedInLuckyBox(uuid: UUID, selectedId: Long)
}

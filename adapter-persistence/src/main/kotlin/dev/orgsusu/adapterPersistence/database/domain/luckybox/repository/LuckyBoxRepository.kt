package dev.orgsusu.adapterPersistence.database.domain.luckybox.repository

import dev.orgsusu.adapterPersistence.database.domain.luckybox.entity.LuckyBoxEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface LuckyBoxRepository : JpaRepository<LuckyBoxEntity, UUID> {
    fun findAllByCreatedBy_Id(id: Long): List<LuckyBoxEntity>
}

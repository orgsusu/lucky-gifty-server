package dev.orgsusu.adapterPersistence.database.domain.luckybox.repository

import dev.orgsusu.adapterPersistence.database.domain.luckybox.entity.LuckyBoxEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Repository
interface LuckyBoxRepository : JpaRepository<LuckyBoxEntity, UUID> {
    fun findAllByCreatedBy_Id(id: Long): List<LuckyBoxEntity>

    @Modifying
    @Transactional
    @Query("UPDATE LuckyBoxEntity l SET l.pickedId = :pickedId WHERE l._luckyBoxId = :luckyBoxId")
    fun updatePickedIdWithId(
        @Param("luckyBoxId") luckyBoxId: UUID,
        @Param("pickedId") pickedId: Long
    )
}

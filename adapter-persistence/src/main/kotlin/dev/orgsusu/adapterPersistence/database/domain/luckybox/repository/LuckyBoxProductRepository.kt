package dev.orgsusu.adapterPersistence.database.domain.luckybox.repository

import dev.orgsusu.adapterPersistence.database.domain.luckybox.entity.LuckyBoxProductEntity
import dev.orgsusu.adapterPersistence.database.domain.luckybox.entity.LuckyBoxProductId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Repository
interface LuckyBoxProductRepository : JpaRepository<LuckyBoxProductEntity, LuckyBoxProductId> {
    @Modifying
    @Transactional
    @Query("DELETE FROM LuckyBoxProductEntity l WHERE l.id.luckyBoxId = :luckyBoxId AND l.id.productId = :productId")
    fun deleteByCompositeKey(
        @Param("luckyBoxId") luckyBoxId: UUID,
        @Param("productId") productId: Long
    )
}

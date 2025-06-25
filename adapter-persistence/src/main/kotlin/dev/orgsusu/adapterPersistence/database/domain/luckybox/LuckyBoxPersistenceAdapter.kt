package dev.orgsusu.adapterPersistence.database.domain.luckybox

import dev.orgsusu.adapterPersistence.database.domain.luckybox.entity.LuckyBoxEntity
import dev.orgsusu.adapterPersistence.database.domain.luckybox.entity.LuckyBoxProductEntity
import dev.orgsusu.adapterPersistence.database.domain.luckybox.entity.LuckyBoxProductId
import dev.orgsusu.adapterPersistence.database.domain.luckybox.repository.LuckyBoxProductRepository
import dev.orgsusu.adapterPersistence.database.domain.luckybox.repository.LuckyBoxRepository
import dev.orgsusu.adapterPersistence.database.domain.user.entity.toUserDomain
import dev.orgsusu.adapterPersistence.database.domain.user.repository.UserRepository
import dev.orgsusu.domain.luckybox.model.LuckyBoxDomain
import dev.orgsusu.domain.luckybox.model.LuckyBoxProductDomain
import dev.orgsusu.domain.luckybox.model.LuckyBoxSimpleDomain
import dev.orgsusu.domain.luckybox.port.outgoing.LuckyBoxPort
import dev.orgsusu.domain.luckybox.port.outgoing.LuckyBoxProductPort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Component
class LuckyBoxPersistenceAdapter(
    private val luckyBoxRepository: LuckyBoxRepository,
    private val luckyBoxProductRepository: LuckyBoxProductRepository,

    private val userRepository: UserRepository,

    private val luckyBoxMapper: LuckyBoxMapper,
) : LuckyBoxPort, LuckyBoxProductPort {
    override fun getAllLuckyBoxes(userId: Long): List<LuckyBoxSimpleDomain> {
        return luckyBoxRepository.findAllByCreatedBy_Id(userId)
            .map { luckyBoxMapper.entityToDomain(it) }
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun createLuckyBox(userId: Long, name: String): LuckyBoxDomain? {
        val user = userRepository.findByIdOrNull(userId) ?: return null

        val saved = luckyBoxRepository.save(
            LuckyBoxEntity(
                createdBy = user,
                name = name,
            )
        )

        return LuckyBoxDomain(
            saved.luckyBoxId,
            saved.name,
            user.toUserDomain(),
            null,
            emptyList(),

            saved.createdAt,
            saved.updatedAt,
        )
    }

    override fun getLuckyBoxById(uuid: UUID): LuckyBoxDomain? {
        val foundLuckyBox = luckyBoxRepository.findByIdOrNull(uuid) ?: return null

        return LuckyBoxDomain(
            foundLuckyBox.luckyBoxId,
            foundLuckyBox.name,
            foundLuckyBox.createdBy.toUserDomain(),
            foundLuckyBox.pickedId
                ?.let { picked -> foundLuckyBox.products.find { it.id.productId == picked } }
                ?.let { luckyBoxMapper.entityToDomain(it) },
            foundLuckyBox.products.map { luckyBoxMapper.entityToDomain(it) },

            foundLuckyBox.createdAt,
            foundLuckyBox.updatedAt,
        )
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun deleteLuckyBoxById(uuid: UUID) = luckyBoxRepository.deleteById(uuid)

    override fun setSelectedInLuckyBox(
        uuid: UUID,
        selectedId: Long
    ) = luckyBoxRepository.updatePickedIdWithId(uuid, selectedId)

    @Transactional(rollbackFor = [Exception::class])
    override fun createProduct(
        uuid: UUID,
        id: Long,
        name: String,
        imageUrl: String?,
        price: Int,
        brandName: String
    ): LuckyBoxProductDomain? {
        val foundLuckyBox = luckyBoxRepository.findByIdOrNull(uuid) ?: return null

        val existingProduct = luckyBoxProductRepository.findByIdOrNull(LuckyBoxProductId(uuid, id))
        if (existingProduct != null)
            return luckyBoxMapper.entityToDomain(existingProduct)

        val savedProduct = luckyBoxProductRepository.save(
            LuckyBoxProductEntity(
                id = LuckyBoxProductId(uuid, id),
                name = name,
                imageUrl = imageUrl,
                price = price,
                brandName = brandName,
                luckyBox = foundLuckyBox
            )
        )

        return luckyBoxMapper.entityToDomain(savedProduct)
    }

    @Transactional(rollbackFor = [Exception::class])
    override fun removeProductById(uuid: UUID, id: Long) {
        luckyBoxProductRepository.deleteByCompositeKey(uuid, id)
    }
}

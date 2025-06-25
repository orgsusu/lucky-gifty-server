package dev.orgsusu.application.domain.luckybox.service

import dev.orgsusu.application.domain.auth.exception.AuthExceptionDetails
import dev.orgsusu.application.domain.gift.exception.GiftExceptionDetails
import dev.orgsusu.application.domain.luckybox.exception.LuckyBoxExceptionDetails
import dev.orgsusu.application.domain.user.service.UserContextHolder
import dev.orgsusu.common.exception.CustomException
import dev.orgsusu.domain.kakao.port.outgoing.KakaoApiPort
import dev.orgsusu.domain.luckybox.model.LuckyBoxDomain
import dev.orgsusu.domain.luckybox.model.LuckyBoxProductDomain
import dev.orgsusu.domain.luckybox.model.LuckyBoxSimpleDomain
import dev.orgsusu.domain.luckybox.port.ingoing.LuckyBoxUseCase
import dev.orgsusu.domain.luckybox.port.outgoing.LuckyBoxPort
import dev.orgsusu.domain.luckybox.port.outgoing.LuckyBoxProductPort
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class LuckyBoxServiceImpl(
    private val luckyBoxPort: LuckyBoxPort,
    private val luckyBoxProductPort: LuckyBoxProductPort,

    private val kakaoApiPort: KakaoApiPort,

    private val userContextHolder: UserContextHolder,
) : LuckyBoxUseCase {
    override fun getAllLuckyBoxes(): List<LuckyBoxSimpleDomain> {
        val userId = userContextHolder.getCurrentUserId()
            ?: throw CustomException(AuthExceptionDetails.UNAUTHORIZED)

        return luckyBoxPort.getAllLuckyBoxes(userId)
    }

    override fun createLuckyBox(name: String): LuckyBoxDomain {
        val userId = userContextHolder.getCurrentUserId()
            ?: throw CustomException(AuthExceptionDetails.UNAUTHORIZED)

        val created = luckyBoxPort.createLuckyBox(userId, name)
            ?: throw CustomException(AuthExceptionDetails.UNAUTHORIZED) // null when user not found

        return created
    }

    override fun getLuckyBoxById(uuid: UUID): LuckyBoxDomain {
        val userId = userContextHolder.getCurrentUserId()
            ?: throw CustomException(AuthExceptionDetails.UNAUTHORIZED)

        val created = luckyBoxPort.getLuckyBoxById(uuid)
            ?: throw CustomException(LuckyBoxExceptionDetails.LUCKY_BOX_NOT_FOUND, uuid)

        if (created.createdBy.id != userId) // TODO: should access when user is invited by our url
            throw CustomException(LuckyBoxExceptionDetails.LUCKY_BOX_NOT_FOUND, uuid)

        return created
    }

    override fun deleteLuckyBoxById(uuid: UUID) {
        val luckyBox = getLuckyBoxById(uuid) // validate mine
        luckyBoxPort.deleteLuckyBoxById(luckyBox.id)
    }

    override fun addProductOnLuckyBox(
        uuid: UUID,
        id: Long
    ): LuckyBoxProductDomain {
        val luckyBox = getLuckyBoxById(uuid)

        val gift = kakaoApiPort.getGiftDetail(id)
            ?: throw CustomException(GiftExceptionDetails.GIFT_NOT_FOUND, id)

        val createdProduct = luckyBoxProductPort.createProduct(
            luckyBox.id,
            id,
            gift.name,
            gift.imageUrl,
            gift.basicPrice, // og price
            gift.brand.name
        ) ?: throw CustomException(
            LuckyBoxExceptionDetails.LUCKY_BOX_NOT_FOUND,
            uuid
        ) // null when lucky box is not found

        return createdProduct
    }

    override fun deleteProductOnLuckyBox(uuid: UUID, id: Long) {
        val luckyBox = getLuckyBoxById(uuid)

        luckyBoxProductPort.removeProductById(luckyBox.id, id)
    }
}

package dev.orgsusu.application.domain.gift.service

import dev.orgsusu.application.domain.gift.exception.GiftExceptionDetails
import dev.orgsusu.common.exception.CustomException
import dev.orgsusu.domain.kakao.model.KakaoTagDomain
import dev.orgsusu.domain.kakao.model.ProductDomain
import dev.orgsusu.domain.kakao.model.ProductSearchResultDomain
import dev.orgsusu.domain.kakao.model.ProductWithReviewDomain
import dev.orgsusu.domain.kakao.model.consts.PriceRange
import dev.orgsusu.domain.kakao.model.consts.RankType
import dev.orgsusu.domain.kakao.model.consts.TargetType
import dev.orgsusu.domain.kakao.port.ingoing.GiftUseCase
import dev.orgsusu.domain.kakao.port.outgoing.KakaoApiPort
import org.springframework.stereotype.Service

@Service
class GiftServiceImpl(
    private val kakaoApiPort: KakaoApiPort,
) : GiftUseCase {
    override fun getRankingTargeted(
        target: TargetType,
        rank: RankType
    ): List<ProductDomain> = kakaoApiPort.getRankingTargeted(target, rank)

    override fun getTags(): List<KakaoTagDomain> = kakaoApiPort.getTags()

    override fun getRankingWithReviews(
        tag: String,
        range: PriceRange
    ): List<ProductWithReviewDomain> = kakaoApiPort.getRankingWithReviews(tag, range) ?: emptyList()

    override fun getAllDeliveryRanking(
        page: Int,
        size: Int
    ): List<ProductDomain> = kakaoApiPort.getAllDeliveryRanking(page, size)

    override fun getAllCouponRanking(
        page: Int,
        size: Int
    ): List<ProductDomain> = kakaoApiPort.getAllCouponRanking(page, size)

    override fun searchGift(
        term: String,
        page: Int
    ): ProductSearchResultDomain = kakaoApiPort.searchGift(term, page) ?: EMPTY_RESULT_DOMAIN

    override fun getGiftDetail(id: Long): ProductDomain = kakaoApiPort.getGiftDetail(id)
        ?: throw CustomException(GiftExceptionDetails.GIFT_NOT_FOUND)

    private companion object {
        private val EMPTY_RESULT_DOMAIN = ProductSearchResultDomain(
            total = 0,
            last = true,
            data = emptyList(),
        )
    }

}

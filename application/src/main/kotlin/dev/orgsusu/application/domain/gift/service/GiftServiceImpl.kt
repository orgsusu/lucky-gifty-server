package dev.orgsusu.application.domain.gift.service

import dev.orgsusu.domain.kakao.model.KakaoTagDomain
import dev.orgsusu.domain.kakao.model.ProductDomain
import dev.orgsusu.domain.kakao.model.ProductSearchResultDomain
import dev.orgsusu.domain.kakao.model.ProductWithReviewDomain
import dev.orgsusu.domain.kakao.model.consts.PriceRange
import dev.orgsusu.domain.kakao.model.consts.RankType
import dev.orgsusu.domain.kakao.model.consts.TargetType
import dev.orgsusu.domain.kakao.port.outgoing.KakaoApiPort
import org.springframework.stereotype.Service

@Service
class GiftServiceImpl(
    private val kakaoApiPort: KakaoApiPort,
) : GiftService {
    override fun getRankingTargeted(
        target: TargetType,
        rank: RankType
    ): List<ProductDomain> = kakaoApiPort.getRankingTargeted(target, rank)

    override fun getTags(): List<KakaoTagDomain> = kakaoApiPort.getTags()

    override fun getRankingWithReviews(
        tag: String,
        range: PriceRange
    ): List<ProductWithReviewDomain> = kakaoApiPort.getRankingWithReviews(tag, range)

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
    ): ProductSearchResultDomain = kakaoApiPort.searchGift(term, page)
}

package dev.orgsusu.domain.kakao.port.ingoing

import dev.orgsusu.domain.kakao.model.KakaoTagDomain
import dev.orgsusu.domain.kakao.model.ProductDomain
import dev.orgsusu.domain.kakao.model.ProductSearchResultDomain
import dev.orgsusu.domain.kakao.model.ProductWithReviewDomain
import dev.orgsusu.domain.kakao.model.consts.PriceRange
import dev.orgsusu.domain.kakao.model.consts.RankType
import dev.orgsusu.domain.kakao.model.consts.TargetType

interface GiftUseCase {
    fun getRankingTargeted(target: TargetType, rank: RankType): List<ProductDomain>
    fun getTags(): List<KakaoTagDomain>
    fun getRankingWithReviews(tag: String, range: PriceRange): List<ProductWithReviewDomain>
    fun getAllDeliveryRanking(page: Int, size: Int): List<ProductDomain>
    fun getAllCouponRanking(page: Int, size: Int): List<ProductDomain>

    fun searchGift(term: String, page: Int): ProductSearchResultDomain
    fun getGiftDetail(id: Long): ProductDomain
}

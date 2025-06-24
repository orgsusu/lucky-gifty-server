package dev.orgsusu.adapterWeb.domain.gift.controller

import dev.orgsusu.common.response.ResponseData
import dev.orgsusu.domain.kakao.model.KakaoTagDomain
import dev.orgsusu.domain.kakao.model.ProductDomain
import dev.orgsusu.domain.kakao.model.ProductSearchResultDomain
import dev.orgsusu.domain.kakao.model.ProductWithReviewDomain
import dev.orgsusu.domain.kakao.model.consts.PriceRange
import dev.orgsusu.domain.kakao.model.consts.RankType
import dev.orgsusu.domain.kakao.model.consts.TargetType
import dev.orgsusu.domain.kakao.port.ingoing.GiftUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/gift")
class GiftController(
    private val giftUseCase: GiftUseCase
) {
    @GetMapping("/rank")
    fun getDeliveryRanking(
        @RequestParam(required = false, defaultValue = "ALL") target: TargetType,
        @RequestParam(required = false, defaultValue = "MANY_WISH") range: RankType,
    ): ResponseEntity<ResponseData<List<ProductDomain>>> {
        val data = giftUseCase.getRankingTargeted(target, range)
        return ResponseData.ok(data = data)
    }

    @GetMapping("/rank/coupon")
    fun getCouponRanking(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "20") size: Int
    ): ResponseEntity<ResponseData<List<ProductDomain>>> {
        val data = giftUseCase.getAllCouponRanking(page, size)
        return ResponseData.ok(data = data)
    }

    @GetMapping("/rank/delivery")
    fun getDeliveryRanking(
        @RequestParam(required = false, defaultValue = "0") page: Int,
        @RequestParam(required = false, defaultValue = "20") size: Int
    ): ResponseEntity<ResponseData<List<ProductDomain>>> {
        val data = giftUseCase.getAllDeliveryRanking(page, size)
        return ResponseData.ok(data = data)
    }

    @GetMapping("/tags")
    fun getTags(): ResponseEntity<ResponseData<List<KakaoTagDomain>>> {
        val data = giftUseCase.getTags()
        return ResponseData.ok(data = data)
    }

    @GetMapping("/rank/with-review")
    fun getRankingWithReviewByTag(
        @RequestParam tag: String,
        @RequestParam range: PriceRange,
    ): ResponseEntity<ResponseData<List<ProductWithReviewDomain>>> {
        val data = giftUseCase.getRankingWithReviews(tag, range)
        return ResponseData.ok(data = data)
    }

    @GetMapping("/search")
    fun search(
        @RequestParam term: String,
        @RequestParam(required = false, defaultValue = "0") page: Int,
    ): ResponseEntity<ResponseData<ProductSearchResultDomain>> {
        val data = giftUseCase.searchGift(term, page)
        return ResponseData.ok(data = data)
    }

    @GetMapping("/{id}")
    fun getOneGiftDetail(
        @PathVariable id: Long,
    ): ResponseEntity<ResponseData<ProductDomain>> {
        val data = giftUseCase.getGiftDetail(id)
        return ResponseData.ok(data = data)
    }
}

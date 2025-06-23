package dev.orgsusu.adapterkakao.domain

import dev.orgsusu.adapterkakao.domain.dto.response.KakaoTagResponseDto
import dev.orgsusu.adapterkakao.domain.dto.response.ProductWithReviewResponseDto
import dev.orgsusu.adapterkakao.domain.dto.response.ProductSearchResultResponseDto
import dev.orgsusu.adapterkakao.domain.dto.response.TargetedRankingResponseDto
import dev.orgsusu.adapterkakao.domain.dto.response.TypedRankingResponseDto
import dev.orgsusu.adapterkakao.domain.mapper.KakaoDtoMapper
import dev.orgsusu.adapterkakao.global.consts.V1
import dev.orgsusu.adapterkakao.global.consts.V2
import dev.orgsusu.domain.kakao.model.KakaoTagDomain
import dev.orgsusu.domain.kakao.model.ProductDomain
import dev.orgsusu.domain.kakao.model.ProductSearchResultDomain
import dev.orgsusu.domain.kakao.model.ProductWithReviewDomain
import dev.orgsusu.domain.kakao.model.consts.PriceRange
import dev.orgsusu.domain.kakao.model.consts.RankType
import dev.orgsusu.domain.kakao.model.consts.TargetType
import dev.orgsusu.domain.kakao.port.outgoing.KakaoApiPort
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import reactor.core.publisher.Flux

@Component
class KakaoApiAdapter(
    private val kakaoClient: WebClient,
    private val kakaoDtoMapper: KakaoDtoMapper,
) : KakaoApiPort {
    override fun getRankingTargeted(
        target: TargetType,
        rank: RankType
    ): List<ProductDomain> {
        return kakaoClient
            .get()
            .uri {
                it
                    .path("$V2/home/ranking/products")
                    .queryParam("targetType", target.name)
                    .queryParam("rankType", rank.name)
                    .build()
            }
            .exchangeToFlux { it.bodyToFlux<TargetedRankingResponseDto>() }
            .map { kakaoDtoMapper.toProductDomain(it.rankingItem) }
            .toIterable() // map to blockable iteration
            .toList() // actual block
    }

    override fun getTags(): List<KakaoTagDomain> {
        return kakaoClient
            .get()
            .uri {
                it
                    .path("$V1/review-rankings/display-tags")
                    .build()
            }
            .exchangeToFlux { it.bodyToFlux<KakaoTagResponseDto>() }
            .map { kakaoDtoMapper.toKakaoTagDomain(it) }
            .toIterable() // map to blockable iteration
            .toList() // actual block
    }

    override fun getRankingWithReviews(
        tag: String,
        range: PriceRange
    ): List<ProductWithReviewDomain> {
        return kakaoClient
            .get()
            .uri {
                it
                    .path("$V2/review-rankings")
                    .queryParam("displayTag", tag)
                    .queryParam("priceRange", range.name)
                    .build()
            }
            .exchangeToFlux { it.bodyToFlux<ProductWithReviewResponseDto>() }
            .map { kakaoDtoMapper.toProductWithReviewDomain(it) }
            .toIterable() // map to blockable iteration
            .toList() // actual block
    }

    override fun getAllDeliveryRanking(page: Int, size: Int): List<ProductDomain> {
        return kakaoClient
            .get()
            .uri {
                it
                    .path("$V2/best/delivery/all")
                    .queryParam("page", page)
                    .queryParam("size", size)
                    .build()
            }
            .exchangeToFlux { it.bodyToFlux<TypedRankingResponseDto>() }
            .filterIsInstance<TypedRankingResponseDto.RankingView>()
            .map { kakaoDtoMapper.toProductDomain(it.product) }
            .toIterable() // map to blockable iteration
            .toList() // actual block
    }

    override fun getAllCouponRanking(page: Int, size: Int): List<ProductDomain> {
        return kakaoClient
            .get()
            .uri {
                it
                    .path("$V2/best/coupon/all")
                    .queryParam("page", page)
                    .queryParam("size", size)
                    .build()
            }
            .exchangeToFlux { it.bodyToFlux<TypedRankingResponseDto>() }
            .filterIsInstance<TypedRankingResponseDto.RankingView>()
            .map { kakaoDtoMapper.toProductDomain(it.product) }
            .toIterable() // map to blockable iteration
            .toList() // actual block
    }

    override fun searchGift(term: String, page: Int): List<ProductSearchResultDomain> {
        return kakaoClient
            .get()
            .uri {
                it
                    .path("/gift-explorer$V1/search/products")
                    .queryParam("page", page)
                    .queryParam("query", term)
                    .build()
            }
            .exchangeToFlux { it.bodyToFlux<ProductSearchResultResponseDto>() }
            .map { kakaoDtoMapper.toProductSearchResultDomain(it) }
            .toIterable() // map to blockable iteration
            .toList() // actual block

    }

    // inspired by List#filterIsInstance
    @Suppress("UNCHECKED_CAST")
    private inline fun <reified R : Any> Flux<*>.filterIsInstance(): Flux<R> = filter { it is R } as Flux<R>
}

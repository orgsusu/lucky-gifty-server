package dev.orgsusu.adapterkakao.domain.mapper

import dev.orgsusu.adapterkakao.domain.dto.ProductBrandDto
import dev.orgsusu.adapterkakao.domain.dto.ProductDto
import dev.orgsusu.adapterkakao.domain.dto.ProductPriceDto
import dev.orgsusu.adapterkakao.domain.dto.response.KakaoTagResponseDto
import dev.orgsusu.adapterkakao.domain.dto.response.ProductWithReviewResponseDto
import dev.orgsusu.adapterkakao.domain.dto.response.ProductSearchResultResponseDto
import dev.orgsusu.domain.kakao.model.KakaoTagDomain
import dev.orgsusu.domain.kakao.model.ProductBrandDomain
import dev.orgsusu.domain.kakao.model.ProductDomain
import dev.orgsusu.domain.kakao.model.ProductPriceDomain
import dev.orgsusu.domain.kakao.model.ProductSearchResultDomain
import dev.orgsusu.domain.kakao.model.ProductWithReviewDomain
import io.mcarle.konvert.api.Konvert
import io.mcarle.konvert.api.Konverter
import io.mcarle.konvert.api.Mapping
import io.mcarle.konvert.injector.spring.KComponent
import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

@Konverter
@KComponent
interface KakaoDtoMapper {
    fun toKakaoTagDomain(dto: KakaoTagResponseDto): KakaoTagDomain

    fun toProductDomain(dto: ProductDto): ProductDomain
    fun toDomain(dto: ProductPriceDto): ProductPriceDomain
    fun toDomain(dto: ProductBrandDto): ProductBrandDomain

    @Konvert(
        mappings = [
            Mapping(target = "createdAt", source = "displayDate")
        ]
    )
    fun toProductWithReviewDomain(dto: ProductWithReviewResponseDto): ProductWithReviewDomain

    @Konvert(
        mappings = [
            Mapping(target = "total", source = "totalCount"),
            Mapping(target = "data", source = "contents"),
        ]
    )
    fun toProductSearchResultDomain(dto: ProductSearchResultResponseDto): ProductSearchResultDomain

    private companion object {
        private val formatter = DateTimeFormatterBuilder()
            .appendValue(ChronoField.YEAR, 4)
            .appendValue(ChronoField.MONTH_OF_YEAR, 2)
            .appendValue(ChronoField.DAY_OF_MONTH, 2)
            .appendValue(ChronoField.HOUR_OF_DAY, 2)
            .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
            .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
            .toFormatter()
    }

    fun numberOnlyStringToLocalDateTime(string: String): LocalDateTime {
        return LocalDateTime.parse(string, formatter)
    }
}

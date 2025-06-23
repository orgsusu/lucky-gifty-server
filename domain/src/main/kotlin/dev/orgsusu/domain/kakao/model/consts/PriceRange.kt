package dev.orgsusu.domain.kakao.model.consts

/**
 * PriceRange
 *
 * 상품의 가격 범위 (단위: n만원)
 *
 * R_0_1: 1만원 미만
 * R_1_3: 1만원 이상 3만원 미만
 * ...
 */
enum class PriceRange {
    R_0_1,
    R_1_3,
    R_3_5,
    R_5,
}

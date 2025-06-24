package dev.orgsusu.application.domain.gift.exception

import dev.orgsusu.common.exception.ExceptionDetail
import org.springframework.http.HttpStatus

enum class GiftExceptionDetails(
    override val message: String,
    override val status: HttpStatus
) : ExceptionDetail {
    GIFT_NOT_FOUND("'%s'에 해당하는 선물을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    ;

    override val code: String = name
}

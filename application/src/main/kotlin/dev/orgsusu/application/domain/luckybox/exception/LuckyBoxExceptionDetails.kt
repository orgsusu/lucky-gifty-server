package dev.orgsusu.application.domain.luckybox.exception

import dev.orgsusu.common.exception.ExceptionDetail
import org.springframework.http.HttpStatus

enum class LuckyBoxExceptionDetails(
    override val message: String,
    override val status: HttpStatus
) : ExceptionDetail {
    LUCKY_BOX_NOT_FOUND("아이디가 '%s'인 럭키박스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    PRODUCT_IS_ALREADY_SET("이미 열린 럭키박스입니다.", HttpStatus.BAD_REQUEST),
    ;

    override val code: String = name
}

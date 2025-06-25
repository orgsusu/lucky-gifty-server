package dev.orgsusu.application.domain.luckybox.exception

import dev.orgsusu.common.exception.ExceptionDetail
import org.springframework.http.HttpStatus

enum class LuckyBoxExceptionDetails(
    override val message: String,
    override val status: HttpStatus
) : ExceptionDetail {
    LUCKY_BOX_NOT_FOUND("아이디가 '%s'인 럭키박스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    ;

    override val code: String = name
}

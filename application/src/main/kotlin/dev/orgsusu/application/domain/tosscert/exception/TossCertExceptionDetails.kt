package dev.orgsusu.application.domain.tosscert.exception

import dev.orgsusu.common.exception.ExceptionDetail
import org.springframework.http.HttpStatus

enum class TossCertExceptionDetails(
    override val message: String, override val status: HttpStatus
) : ExceptionDetail {
    FAIL_TO_FETCH("값을 받아오는데 실패했습니다", HttpStatus.BAD_REQUEST),
    ;

    override val code = name
}
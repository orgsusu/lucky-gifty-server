package dev.orgsusu.application.exception

import dev.orgsusu.common.exception.ExceptionDetail
import org.springframework.http.HttpStatus

enum class AuthExceptionDetails(override val message: String, override val status: HttpStatus) : ExceptionDetail {
    BAD_CREDENTIALS("아이디 또는 비밀번호가 잘못 되었음", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED("잘못된 접근입니다.", HttpStatus.UNAUTHORIZED),
    ;
    override val code = name
}

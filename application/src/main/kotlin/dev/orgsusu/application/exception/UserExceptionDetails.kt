package dev.orgsusu.application.exception

import dev.orgsusu.common.exception.ExceptionDetail
import org.springframework.http.HttpStatus

enum class UserExceptionDetails(
    override val message: String, override val status: HttpStatus
) : ExceptionDetail {
    BAD_CREDENTIALS("아이디 또는 비밀번호가 잘못 되었습니다", HttpStatus.UNAUTHORIZED),
    USER_ALREADY_EXISTS("'%s' 사용자가 이미 존재합니다", HttpStatus.BAD_REQUEST),
    SESSION_USER_NOT_FOUND("유저를 찾을 수 없습니다", HttpStatus.UNAUTHORIZED),
    AUTHENTICATION_FAILED("인증에 실패했습니다", HttpStatus.UNAUTHORIZED),
    ;

    override val code = name
}
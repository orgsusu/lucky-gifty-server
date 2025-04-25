package dev.orgsusu.application.exception

import dev.orgsusu.common.exception.ExceptionDetail
import org.springframework.http.HttpStatus

enum class UserExceptionDetails(
    override val message: String, override val status: HttpStatus
) : ExceptionDetail {
    BAD_CREDENTIALS("아이디 또는 비밀번호가 잘못 되었습니다", HttpStatus.UNAUTHORIZED),
    USER_ALREADY_EXISTS("'%s' 사용자가 이미 존재합니다", HttpStatus.BAD_REQUEST),
    INVALID_SESSION("유효하지 않은 세션입니다", HttpStatus.UNAUTHORIZED),
    SESSION_USER_NOT_FOUND("유저를 찾을 수 없습니다", HttpStatus.UNAUTHORIZED),
    SESSION_NOT_FOUND("세션을 찾을 수 없습니다", HttpStatus.UNAUTHORIZED),
    AUTHENTICATION_FAILED("인증에 실패했습니다", HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED("접근 권한이 없습니다", HttpStatus.FORBIDDEN),
    UNPROCESSABLE_REQUEST("처리할 수 없는 요청입니다", HttpStatus.UNPROCESSABLE_ENTITY),
    ;

    override val code = name
}
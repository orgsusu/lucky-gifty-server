package dev.orgsusu.adapterWeb.controller

import dev.orgsusu.adapterWeb.controller.dto.request.LoginRequestDto
import dev.orgsusu.adapterWeb.controller.dto.request.RegisterRequestDto
import dev.orgsusu.adapterWeb.controller.dto.response.UserResponseDto
import dev.orgsusu.common.response.ResponseData
import dev.orgsusu.common.response.ResponseEmpty
import dev.orgsusu.domain.port.incoming.UserUseCase
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import dev.orgsusu.domain.handler.SessionLogoutHandler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val userUseCase: UserUseCase,
    private val sessionLogoutHandler: SessionLogoutHandler
) {

    @PostMapping("/login")
    fun authenticate(
        @RequestBody @Valid request: LoginRequestDto, servletRequest: HttpServletRequest): ResponseEntity<ResponseData<UserResponseDto>> {
        val user = userUseCase.authenticateUser(request.credential, request.password)
        servletRequest.login(request.credential, request.password)
        return ResponseData.ok(data = UserResponseDto.fromDomain(user))
    }

    @PostMapping("/register")
    fun createMember(@RequestBody @Valid request: RegisterRequestDto): ResponseEntity<ResponseData<UserResponseDto>> {
        val user = userUseCase.registerUser(request.toPartialUser())
        return ResponseData.created(data = UserResponseDto.fromDomain(user))
    }

    @PostMapping("/logout")
    fun logout(request: HttpServletRequest): ResponseEntity<ResponseEmpty> {
        val session = request.getSession(false)
        val sessionId = session?.id
        session?.invalidate()
        
        sessionLogoutHandler.logoutSession(sessionId)
        sessionLogoutHandler.clearSecurityContext()

        return ResponseEmpty.ok()
    }

    @GetMapping("/check-credential")
    fun checkCredentialAvailability(@RequestParam credential: String): ResponseEntity<ResponseData<Boolean>> {
        val isAvailable = userUseCase.isCredentialAvailable(credential)
        return ResponseData.ok(data = isAvailable)
    }
}

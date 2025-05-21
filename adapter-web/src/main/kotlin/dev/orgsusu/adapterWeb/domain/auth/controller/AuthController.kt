package dev.orgsusu.adapterWeb.domain.auth.controller

import dev.orgsusu.adapterWeb.domain.auth.dto.request.*
import dev.orgsusu.common.response.ResponseData
import dev.orgsusu.common.response.ResponseEmpty
import dev.orgsusu.domain.user.port.incoming.UserUseCase
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val userUseCase: UserUseCase,
) {
    @GetMapping
    fun checkCredentialAvailability(@RequestParam credential: String): ResponseEntity<ResponseData<Boolean>> {
        val isAvailable = userUseCase.checkCredentialAvailable(credential)
        return ResponseData.ok(data = isAvailable)
    }

    @PostMapping("/login")
    fun authenticate(
        @RequestBody @Valid request: LoginRequestDto, servletRequest: HttpServletRequest
    ): ResponseEntity<ResponseEmpty> {
        servletRequest.login(request.credential, request.password)
        return ResponseEmpty.ok()
    }
}

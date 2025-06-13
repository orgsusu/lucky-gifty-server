package dev.orgsusu.adapterWeb.domain.user.controller

import dev.orgsusu.adapterWeb.domain.user.dto.request.*
import dev.orgsusu.adapterWeb.domain.user.dto.response.*
import dev.orgsusu.common.response.ResponseData
import dev.orgsusu.common.response.ResponseEmpty
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import dev.orgsusu.domain.user.port.incoming.UserUseCase

@RestController
@RequestMapping("/users")
class UserController(
    private val userUseCase: UserUseCase
) {
    @PostMapping
    fun createMember(@RequestBody @Valid request: CreateUserRequestDto): ResponseEntity<ResponseData<UserResponseDto>> {
        val user = userUseCase.registerUser(request.toPartialUserDomain())
        return ResponseData.created(data = UserResponseDto.Companion.fromUserDomain(user))
    }

    @GetMapping("/me")
    fun getCurrentUser(): ResponseEntity<ResponseData<UserResponseDto>> {
        val user = userUseCase.getCurrentUser()
        return ResponseData.ok(data = UserResponseDto.Companion.fromUserDomain(user))
    }

    @PutMapping("/me")
    fun updateUserInfo(@RequestBody @Valid request: UpdateUserRequestDto): ResponseEntity<ResponseData<UserResponseDto>> {
        val currentUser = userUseCase.getCurrentUser()
        val updatedUser = userUseCase.updateUserInfo(
            id = currentUser.id,
            phone = request.phone,
            email = request.email,
            birthDay = request.birthDay
        )
        return ResponseData.ok(data = UserResponseDto.Companion.fromUserDomain(updatedUser))
    }

    @DeleteMapping("/me")
    fun deleteUser(): ResponseEntity<ResponseEmpty> {
        val currentUser = userUseCase.getCurrentUser()
        userUseCase.deleteUser(currentUser.id)
        return ResponseEmpty.ok()
    }
}

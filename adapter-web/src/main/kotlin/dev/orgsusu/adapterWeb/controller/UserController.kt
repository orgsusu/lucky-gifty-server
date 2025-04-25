package dev.orgsusu.adapterWeb.controller

import dev.orgsusu.adapterWeb.controller.dto.request.UpdateUserRequestDto
import dev.orgsusu.adapterWeb.controller.dto.response.UserResponseDto
import dev.orgsusu.common.response.ResponseData
import dev.orgsusu.common.response.ResponseEmpty
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import dev.orgsusu.domain.port.incoming.UserUseCase

@RestController
@RequestMapping("/user")
class UserController(
    private val userUseCase: UserUseCase
) {

    @GetMapping("/me")
    fun getCurrentUser(): ResponseEntity<ResponseData<UserResponseDto>> {
        val user = userUseCase.getCurrentUser()
        return ResponseData.ok(data = UserResponseDto.fromDomain(user))
    }

    @PutMapping("/me")
    fun updateUserInfo(@RequestBody @Valid request: UpdateUserRequestDto): ResponseEntity<ResponseData<UserResponseDto>> {
        val currentUser = userUseCase.getCurrentUser()
        val updatedUser = userUseCase.updateUserInfo(
            id = currentUser.id,
            phoneNum = request.phoneNum,
            mail = request.mail,
            birthDate = request.birthDate
        )
        return ResponseData.ok(data = UserResponseDto.fromDomain(updatedUser))
    }

    @DeleteMapping("/me")
    fun deleteUser(): ResponseEntity<ResponseEmpty> {
        val currentUser = userUseCase.getCurrentUser()
        userUseCase.deleteUser(currentUser.id)
        return ResponseEmpty.ok()
    }
}
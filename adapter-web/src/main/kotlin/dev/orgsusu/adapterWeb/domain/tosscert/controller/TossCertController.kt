package dev.orgsusu.adapterWeb.domain.tosscert.controller

import dev.orgsusu.common.response.ResponseData
import dev.orgsusu.domain.tosscert.model.TossCertTokenResponse
import dev.orgsusu.domain.tosscert.port.ingoing.TossCertTokenUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/toss")
class TossCertController (
    private val tossCertTokenUseCase: TossCertTokenUseCase
){
    @GetMapping("/token")
    fun issueToken():ResponseEntity<ResponseData<TossCertTokenResponse>>{
        val token = tossCertTokenUseCase.issueAccessToken()
        return ResponseData.ok(data = token)
    }
}
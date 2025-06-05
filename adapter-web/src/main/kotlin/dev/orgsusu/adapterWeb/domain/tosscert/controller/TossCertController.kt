package dev.orgsusu.adapterWeb.domain.tosscert.controller

import dev.orgsusu.common.response.ResponseData
import dev.orgsusu.domain.tosscert.model.response.TossCertTxIdSuccessResponseDomain
import dev.orgsusu.domain.tosscert.port.ingoing.TossCertUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/toss")
class TossCertController (
    private val tossCertTokenUseCase: TossCertUseCase
){
    @GetMapping("/tx-id")
    fun getTxId(): ResponseEntity<ResponseData<TossCertTxIdSuccessResponseDomain>>{
        val txId = tossCertTokenUseCase.getTxId()
        return ResponseData.ok(data = txId)
    }
}
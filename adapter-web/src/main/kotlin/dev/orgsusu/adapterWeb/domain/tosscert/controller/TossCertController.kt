package dev.orgsusu.adapterWeb.domain.tosscert.controller

import dev.orgsusu.common.response.ResponseData
import dev.orgsusu.domain.tosscert.model.response.TossCertErrorResponseDomain
import dev.orgsusu.domain.tosscert.model.wrapper.TossCertStatusResponseWrapper
import dev.orgsusu.domain.tosscert.model.wrapper.TossCertTxIdResponseWrapper
import dev.orgsusu.domain.tosscert.port.ingoing.TossCertUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/toss")
class TossCertController(
    private val tossCertTokenUseCase: TossCertUseCase
) {
    @GetMapping("/tx-id")
    fun getTxId(): ResponseEntity<ResponseData<TossCertTxIdResponseWrapper>> {
        val txId = tossCertTokenUseCase.getTxId()
        return ResponseData.ok(data = txId)
    }

    @GetMapping("/status/{tx-id}")
    fun getStatus(@PathVariable("tx-id") txId: String): ResponseEntity<ResponseData<TossCertStatusResponseWrapper>> {
        val status = tossCertTokenUseCase.getStatus(txId)
        return ResponseData.ok(data = status)
    }

    @GetMapping("/result/{tx-id}")
    fun getResult(@PathVariable("tx-id") txId: String): ResponseEntity<ResponseData<TossCertErrorResponseDomain?>> {
        val result = tossCertTokenUseCase.getResult(txId)
        return ResponseData.ok(data = result)
    }
}
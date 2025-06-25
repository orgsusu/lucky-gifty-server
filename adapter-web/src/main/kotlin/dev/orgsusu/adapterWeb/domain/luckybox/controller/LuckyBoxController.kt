package dev.orgsusu.adapterWeb.domain.luckybox.controller

import dev.orgsusu.adapterWeb.domain.luckybox.dto.request.AddProductRequest
import dev.orgsusu.adapterWeb.domain.luckybox.dto.request.CreateLuckyBoxRequest
import dev.orgsusu.adapterWeb.domain.luckybox.dto.response.LuckyBoxResponseDto
import dev.orgsusu.adapterWeb.domain.luckybox.dto.response.LuckyBoxSimpleResponseDto
import dev.orgsusu.adapterWeb.domain.luckybox.dto.response.fromLuckyBoxDomain
import dev.orgsusu.adapterWeb.domain.luckybox.dto.response.fromLuckyBoxSimpleDomain
import dev.orgsusu.common.response.ResponseData
import dev.orgsusu.common.response.ResponseEmpty
import dev.orgsusu.domain.luckybox.model.LuckyBoxProductDomain
import dev.orgsusu.domain.luckybox.port.ingoing.LuckyBoxUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/lucky-box")
class LuckyBoxController(
    private val luckyBoxUseCase: LuckyBoxUseCase
) {
    @GetMapping
    fun getLuckyBoxes(): ResponseEntity<ResponseData<List<LuckyBoxSimpleResponseDto>>> {
        val data = luckyBoxUseCase.getAllLuckyBoxes()
        return ResponseData.ok(data = data.map { LuckyBoxSimpleResponseDto.fromLuckyBoxSimpleDomain(it) })
    }

    @PostMapping
    fun createLuckyBox(@RequestBody request: CreateLuckyBoxRequest): ResponseEntity<ResponseData<LuckyBoxResponseDto>> {
        // TODO: Image upload
        val data = luckyBoxUseCase.createLuckyBox(request.name)
        return ResponseData.ok(data = LuckyBoxResponseDto.fromLuckyBoxDomain(data))
    }

    @GetMapping("/{uuid}")
    fun openLuckyBox(@PathVariable uuid: UUID): ResponseEntity<ResponseData<LuckyBoxResponseDto>> {
        val data = luckyBoxUseCase.getLuckyBoxById(uuid)
        return ResponseData.ok(data = LuckyBoxResponseDto.fromLuckyBoxDomain(data))
    }

    @DeleteMapping("/{uuid}")
    fun deleteLuckyBoxById(@PathVariable uuid: UUID): ResponseEntity<ResponseEmpty> {
        luckyBoxUseCase.deleteLuckyBoxById(uuid)
        return ResponseEmpty.noContent()
    }

    @PostMapping("/{uuid}")
    fun getLuckyBoxById(@PathVariable uuid: UUID): ResponseEntity<ResponseData<LuckyBoxProductDomain>> {
        val data = luckyBoxUseCase.openLuckyBox(uuid)
        return ResponseData.ok(data = data)
    }

    @PostMapping("/{boxId}/products")
    fun getLuckyBoxById(
        @PathVariable boxId: UUID,
        @RequestBody request: AddProductRequest
    ): ResponseEntity<ResponseData<LuckyBoxProductDomain>> {
        val data = luckyBoxUseCase.addProductOnLuckyBox(boxId, request.productId)
        return ResponseData.ok(data = data)
    }

    @DeleteMapping("/{boxId}/products/{productId}")
    fun deleteLuckyBoxById(
        @PathVariable boxId: UUID,
        @PathVariable productId: Long
    ): ResponseEntity<ResponseEmpty> {
        luckyBoxUseCase.deleteProductOnLuckyBox(boxId, productId)
        return ResponseEmpty.noContent()
    }
}

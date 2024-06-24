package com.musinsa.shop.brand.adapter.`in`.web

import com.musinsa.shop.brand.application.port.`in`.BrandUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/brands")
class BrandController(
    private val brandUseCase: BrandUseCase
) {
    @PostMapping
    fun createBrand(@RequestBody brandCreateReqDto: BrandCreateReqDto): ResponseEntity<BrandResDto> {
        return ResponseEntity(
            brandUseCase.createBrand(
                name = brandCreateReqDto.name,
                code = brandCreateReqDto.code
            ), HttpStatus.CREATED
        )
    }

    @PatchMapping("/{id}")
    fun updateBrand(
        @PathVariable id: Long,
        @RequestBody brandUpdateReqDto: BrandUpdateReqDto
    ): ResponseEntity<Int> {
        return ResponseEntity(
            brandUseCase.updateBrand(
                id = id,
                name = brandUpdateReqDto.name,
                code = brandUpdateReqDto.code,
            ), HttpStatus.OK
        )
    }

    @DeleteMapping("/{id}")
    fun deleteBrand(@PathVariable id: Long): ResponseEntity<Long> {
        brandUseCase.deleteBrand(id)
        return ResponseEntity(id, HttpStatus.NO_CONTENT)
    }
}
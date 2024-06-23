package com.musinsa.shop.brand.adapter.`in`.web

import com.musinsa.shop.brand.application.port.`in`.BrandUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
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
    fun createBrand(@RequestBody brandCreateReqDto: BrandCreateReqDto): BrandResDto {
        return brandUseCase.createBrand(
            name = brandCreateReqDto.name,
            code = brandCreateReqDto.code
        )
    }

    @PatchMapping
    fun updateBrand(@RequestBody brandUpdateReqDto: BrandUpdateDto): BrandResDto {
        return brandUseCase.updateBrand(
            id = brandUpdateReqDto.id,
            name = brandUpdateReqDto.name,
            code = brandUpdateReqDto.code,
        )
    }

    @DeleteMapping
    fun deleteBrand(@RequestBody id: Long) {
        brandUseCase.deleteBrand(id)
    }
}
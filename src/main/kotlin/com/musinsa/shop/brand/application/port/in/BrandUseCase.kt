package com.musinsa.shop.brand.application.port.`in`

import com.musinsa.shop.brand.adapter.`in`.web.BrandResDto

interface BrandUseCase {
    fun createBrand(name: String, code: String): BrandResDto
    fun updateBrand(id: Long, name: String, code: String): BrandResDto
    fun deleteBrand(id: Long)
}
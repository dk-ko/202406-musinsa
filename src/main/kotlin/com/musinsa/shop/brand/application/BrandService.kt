package com.musinsa.shop.brand.application

import com.musinsa.shop.brand.adapter.`in`.web.BrandResDto
import com.musinsa.shop.brand.application.port.`in`.BrandUseCase
import com.musinsa.shop.brand.application.port.out.LoadBrandPort
import com.musinsa.shop.category.application.port.out.LoadCategoryBrandMappingPort
import org.springframework.stereotype.Service

@Service
class BrandService(
    private val loadBrandPort: LoadBrandPort,
    private val loadCategoryBrandMappingPort: LoadCategoryBrandMappingPort,
): BrandUseCase {
    override fun createBrand(name: String, code: String): BrandResDto {
        return loadBrandPort.createBrand(name, code).toResponse()
    }

    override fun updateBrand(id: Long, name: String, code: String): Int {
        return loadBrandPort.updateBrand(id, name, code)
    }

    override fun deleteBrand(id: Long) {
        loadBrandPort.deleteBrand(id)
        loadCategoryBrandMappingPort.deleteByBrandId(id)
    }
}
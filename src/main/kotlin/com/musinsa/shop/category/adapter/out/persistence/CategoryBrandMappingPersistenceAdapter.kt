package com.musinsa.shop.category.adapter.out.persistence

import com.musinsa.shop.category.application.port.out.LoadCategoryBrandMappingPort
import org.springframework.stereotype.Component

@Component
class CategoryBrandMappingPersistenceAdapter(
    private val categoryBrandMappingRepository: CategoryBrandMappingRepository,
): LoadCategoryBrandMappingPort {
    override fun deleteByBrandId(id: Long) {
        categoryBrandMappingRepository.deleteByBrandId(id)
    }
}
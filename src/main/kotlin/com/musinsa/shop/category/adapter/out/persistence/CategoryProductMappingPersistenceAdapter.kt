package com.musinsa.shop.category.adapter.out.persistence

import com.musinsa.shop.category.application.port.out.LoadCategoryProductMappingPort
import org.springframework.stereotype.Component

@Component
class CategoryProductMappingPersistenceAdapter(
    private val categoryProductMappingRepository: CategoryProductMappingRepository,
) : LoadCategoryProductMappingPort {
    override fun updateCategoryProductMapping(
        existingCategoryId: Long,
        productId: Long,
        categoryIdToUpdate: Long
    ): Int {
        return categoryProductMappingRepository.updateCategoryProductMapping(
            existingCategoryId = existingCategoryId,
            productId = productId,
            categoryIdToUpdate = categoryIdToUpdate
        )
    }

    override fun createCategoryProductMapping(categoryProductMapping: CategoryProductMapping): CategoryProductMapping {
        return categoryProductMappingRepository.save(categoryProductMapping)
    }

    override fun createBulkCategoryProductMapping(categoryProductMappings: List<CategoryProductMapping>): List<CategoryProductMapping> {
        return categoryProductMappingRepository.saveAll(categoryProductMappings)
    }
}
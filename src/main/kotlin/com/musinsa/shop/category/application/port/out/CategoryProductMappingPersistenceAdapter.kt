package com.musinsa.shop.category.application.port.out

import com.musinsa.shop.category.adapter.out.persistence.CategoryProductMappingRepository
import org.springframework.stereotype.Component

@Component
class CategoryProductMappingPersistenceAdapter(
    private val categoryProductMappingRepository: CategoryProductMappingRepository,
): LoadCategoryProductMappingPort {
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
}
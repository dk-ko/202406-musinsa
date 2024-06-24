package com.musinsa.shop.category.application.port.out

import com.musinsa.shop.category.adapter.out.persistence.CategoryProductMapping

interface LoadCategoryProductMappingPort {
    fun updateCategoryProductMapping(existingCategoryId: Long, productId: Long, categoryIdToUpdate: Long): Int
    fun createCategoryProductMapping(categoryProductMapping: CategoryProductMapping): CategoryProductMapping
    fun createBulkCategoryProductMapping(categoryProductMappings: List<CategoryProductMapping>): List<CategoryProductMapping>
}
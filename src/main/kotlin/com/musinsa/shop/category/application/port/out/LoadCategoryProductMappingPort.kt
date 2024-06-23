package com.musinsa.shop.category.application.port.out

interface LoadCategoryProductMappingPort {
    fun updateCategoryProductMapping(existingCategoryId: Long, productId: Long, categoryIdToUpdate: Long): Int
}
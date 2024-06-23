package com.musinsa.shop.category.application.port.`in`

import com.musinsa.shop.category.adapter.out.persistence.Category

interface CategoryUseCase {
    fun createRootCategory(name: String): Category
    fun addSubCategory(parentCategoryId: Long, subCategoryName: String): Category
}
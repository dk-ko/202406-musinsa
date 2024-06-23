package com.musinsa.shop.category.application.port.`in`

import com.musinsa.shop.category.adapter.`in`.web.CategoryResDto

interface CategoryUseCase {
    fun createRootCategory(name: String): CategoryResDto
    fun addSubCategory(parentCategoryId: Long, subCategoryName: String): CategoryResDto
    fun getAllRootCategories(): List<CategoryResDto>
    fun getAllSubCategories(parentId: Long): List<CategoryResDto>
    fun getAllSubCategoriesByDepth(parentId: Long, depth: Int): List<CategoryResDto>
}
package com.musinsa.shop.categorypath.adapter.out.persistence

import com.musinsa.shop.category.adapter.out.persistence.Category

interface LoadCategoryPathPort {
    fun createCategoryPath(categoryPath: CategoryPath): CategoryPath
    fun getParentCategoryPaths(parentCategory: Category): List<CategoryPath>
}
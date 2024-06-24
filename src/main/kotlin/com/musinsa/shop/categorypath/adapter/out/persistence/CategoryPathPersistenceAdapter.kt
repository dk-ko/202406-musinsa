package com.musinsa.shop.categorypath.adapter.out.persistence

import com.musinsa.shop.category.adapter.out.persistence.Category
import org.springframework.stereotype.Component

@Component
class CategoryPathPersistenceAdapter(
    private val categoryPathRepository: CategoryPathRepository,
): LoadCategoryPathPort {
    override fun createCategoryPath(categoryPath: CategoryPath): CategoryPath {
        return categoryPathRepository.save(categoryPath)
    }

    override fun getParentCategoryPaths(parentCategory: Category): List<CategoryPath> {
        return categoryPathRepository.findAllByParentCategoryPaths(parentCategoryId = parentCategory.id!!)
    }
}
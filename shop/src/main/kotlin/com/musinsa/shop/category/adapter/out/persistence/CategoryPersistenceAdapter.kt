package com.musinsa.shop.category.adapter.out.persistence

import com.musinsa.shop.category.application.port.out.LoadCategoryPort
import org.springframework.stereotype.Component

@Component
class CategoryPersistenceAdapter(
    private val categoryRepository: CategoryRepository,
): LoadCategoryPort {
    override fun createCategory(name: String): Category {
        return categoryRepository.save(Category(
            name = name,
        ))
    }
}
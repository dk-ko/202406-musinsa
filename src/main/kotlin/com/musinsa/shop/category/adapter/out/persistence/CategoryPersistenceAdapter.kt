package com.musinsa.shop.category.adapter.out.persistence

import com.musinsa.shop.category.application.port.out.LoadCategoryPort
import com.musinsa.shop.exception.NotFoundException
import org.springframework.stereotype.Component

@Component
class CategoryPersistenceAdapter(
    private val categoryRepository: CategoryRepository,
): LoadCategoryPort {
    override fun createCategory(name: String): Category {
        return categoryRepository.save(Category(name = name))
    }

    override fun findCategoryById(id: Long): Category {
        return categoryRepository.findById(id).orElseThrow {
            throw NotFoundException("Category with id $id not found")
        }
    }

    override fun findCategoriesByIds(ids: List<Long>): List<Category> {
        return categoryRepository.findAllById(ids)
    }

    override fun getAllRootCategories(): List<Category> {
        return categoryRepository.findAllRootCategories()
    }

    override fun getAllSubCategories(parentId: Long): List<Category> {
        return categoryRepository.findAllSubCategories(parentId)
    }

    override fun getAllSubCategoriesByDepth(parentId: Long, depth: Int): List<Category> {
        return categoryRepository.findAllSubCategoriesByDepth(parentId, depth)
    }

    override fun getAllCategories(): List<Category> {
        return categoryRepository.findAll()
    }

    override fun findByName(name: String): Category {
        return categoryRepository.findByName(name).orElseThrow{
            throw NotFoundException("Category with name $name not found")
        }
    }
}
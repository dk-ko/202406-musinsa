package com.musinsa.shop.category.adapter.out.persistence

import com.musinsa.shop.category.application.port.out.LoadCategoryPort
import com.musinsa.shop.categorypath.adapter.out.persistence.CategoryPath
import com.musinsa.shop.categorypath.adapter.out.persistence.CategoryPathId
import com.musinsa.shop.categorypath.adapter.out.persistence.CategoryPathRepository
import com.musinsa.shop.exception.NotFoundException
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CategoryPersistenceAdapter(
    private val categoryRepository: CategoryRepository,
    private val categoryPathRepository: CategoryPathRepository,
): LoadCategoryPort {
    @Transactional
    override fun createRootCategory(name: String): Category {
        val category = Category(name = name)
        val savedCategory = categoryRepository.save(category)

        val categoryPathId = CategoryPathId(parentId = savedCategory.id!!, childId = savedCategory.id)
        val categoryPath = CategoryPath(id = categoryPathId, depth = 0, parent = savedCategory, child = savedCategory)
        categoryPathRepository.save(categoryPath)

        return savedCategory
    }

    override fun addSubCategory(parentId: Long, subCategoryName: String): Category {
        // 부모 카테고리 조회
        val parentCategory = this.findCategoryById(parentId)

        // 새로운 서브 카테고리 생성
        val subCategory = Category(name = subCategoryName)
        val savedSubCategory = categoryRepository.save(subCategory)

        // 부모 카테고리를 경로로 하는 category_path 생성
        val parentPaths = categoryPathRepository.findAll().filter { it.id.childId == parentCategory.id }
        parentPaths.forEach { parentPath ->
            val newPathId = CategoryPathId(parentId = parentPath.id.parentId, childId = savedSubCategory.id!!)
            val newPath = CategoryPath(
                id = newPathId,
                depth = parentPath.depth + 1,
                parent = parentPath.parent,
                child = savedSubCategory
            )
            categoryPathRepository.save(newPath)
        }

        return savedSubCategory
    }

    override fun findCategoryById(id: Long): Category {
        return categoryRepository.findById(id).orElseThrow {
            throw NotFoundException("Category with id $id not found")
        }
    }

    override fun getAllRootCategories(): List<Category> {
        TODO("Not yet implemented")
    }

    override fun getAllSubCategories(parentId: Long): List<Category> {
        TODO("Not yet implemented")
    }

    override fun getAllSubCategoriesByDepth(parentId: Long, depth: Int): List<Category> {
        TODO("Not yet implemented")
    }

    override fun getAllCategories(): List<Category> {
        TODO("Not yet implemented")
    }
}
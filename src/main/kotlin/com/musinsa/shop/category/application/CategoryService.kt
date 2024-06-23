package com.musinsa.shop.category.application

import com.musinsa.shop.category.adapter.`in`.web.CategoryResDto
import com.musinsa.shop.category.application.port.`in`.CategoryUseCase
import com.musinsa.shop.category.application.port.out.LoadCategoryPort
import com.musinsa.shop.categorypath.adapter.out.persistence.CategoryPath
import com.musinsa.shop.categorypath.adapter.out.persistence.CategoryPathId
import com.musinsa.shop.categorypath.adapter.out.persistence.LoadCategoryPathPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CategoryService(
    private val loadCategoryPort: LoadCategoryPort,
    private val loadCategoryPathPort: LoadCategoryPathPort,
): CategoryUseCase {
    @Transactional
    override fun createRootCategory(name: String): CategoryResDto {
        val savedCategory = loadCategoryPort.createCategory(name)

        val categoryPathId = CategoryPathId(parentId = savedCategory.id!!, childId = savedCategory.id)
        val categoryPath = CategoryPath(
            id = categoryPathId,
            depth = 0,
            parent = savedCategory,
            child = savedCategory
        )
        loadCategoryPathPort.createCategoryPath(categoryPath)

        return savedCategory.toResponse()
    }

    @Transactional
    override fun addSubCategory(parentCategoryId: Long, subCategoryName: String): CategoryResDto {
        // 부모 카테고리 조회
        val parentCategory = loadCategoryPort.findCategoryById(parentCategoryId)

        // 새로운 서브 카테고리 생성
        val savedSubCategory = loadCategoryPort.createCategory(subCategoryName)

        // 부모 카테고리를 경로로 하는 category_path 생성
        val parentPaths = loadCategoryPathPort.getParentCategoryPaths(parentCategory)
        parentPaths.forEach { parentPath ->
            val newPathId = CategoryPathId(parentId = parentPath.id.parentId, childId = savedSubCategory.id!!)
            val newPath = CategoryPath(
                id = newPathId,
                depth = parentPath.depth + 1,
                parent = parentPath.parent,
                child = savedSubCategory
            )
            loadCategoryPathPort.createCategoryPath(newPath)
        }

        return savedSubCategory.toResponse()
    }

    @Transactional(readOnly = true)
    override fun getAllRootCategories(): List<CategoryResDto> {
        return loadCategoryPort.getAllRootCategories().map { it.toResponse() }
    }

    @Transactional(readOnly = true)
    override fun getAllSubCategories(parentId: Long): List<CategoryResDto> {
        return loadCategoryPort.getAllSubCategories(parentId).map { it.toResponse() }
    }

    @Transactional(readOnly = true)
    override fun getAllSubCategoriesByDepth(parentId: Long, depth: Int): List<CategoryResDto> {
        return loadCategoryPort.getAllSubCategoriesByDepth(parentId, depth).map { it.toResponse() }
    }
}
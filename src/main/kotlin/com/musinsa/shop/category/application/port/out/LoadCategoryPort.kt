package com.musinsa.shop.category.application.port.out

import com.musinsa.shop.category.adapter.out.persistence.Category

interface LoadCategoryPort {
    fun findCategoryById(id: Long): Category
    fun findCategoriesByIds(ids: List<Long>): List<Category>
    // 루트카테고리만 전체 조회
    fun getAllRootCategories(): List<Category>
    // 루트카테고리에 연결된 서브카테고리를 전부 조회
    fun getAllSubCategories(parentId: Long): List<Category>
    // 루트카테고리에 연결된 서브카테고리를 depth를 지정해 조회
    fun getAllSubCategoriesByDepth(parentId: Long, depth: Int): List<Category>
    // 전체 카테고리 조회
    fun getAllCategories(): List<Category>
    fun findByName(name: String): Category
    fun createCategory(name: String): Category
}
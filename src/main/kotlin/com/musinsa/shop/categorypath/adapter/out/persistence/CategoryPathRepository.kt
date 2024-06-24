package com.musinsa.shop.categorypath.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CategoryPathRepository: JpaRepository<CategoryPath, CategoryPathId> {
    @Query(
        value = """
            SELECT cp.* FROM category_path cp
            WHERE cp.child_id = :parentId
        """, nativeQuery = true
    )
    fun findAllByParentCategoryPaths(@Param("parentId") parentCategoryId: Long): List<CategoryPath>
}
package com.musinsa.shop.category.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CategoryRepository: JpaRepository<Category, Long> {
    // 루트카테고리만 전체 조회
    @Query(
        value = """
            SELECT c.* FROM category c
            JOIN category_path cp ON c.category_id = cp.child_id
            WHERE cp.depth = 0
        """, nativeQuery = true
    )
    fun findAllRootCategories(): List<Category>

    // 루트카테고리에 연결된 서브카테고리를 전부 조회
    @Query(
        value = """
            SELECT c.* FROM category c
            JOIN category_path cp ON c.category_id = cp.parent_id
            WHERE cp.parent_id = :parentId 
            AND cp.child_id != :parentId 
            AND cp.depth != 0
        """, nativeQuery = true
    )
    fun findAllSubCategories(@Param("parentId") parentId: Long): List<Category>

    // 루트카테고리에 연결된 서브카테고리를 depth를 지정해 조회
    @Query(
        value = """
            SELECT c.* FROM category c
            JOIN category_path cp ON c.category_id = cp.parent_id
            WHERE cp.parent_id = :parentId
            AND cp.child_id != :parentId
            AND cp.depth = :depth
        """, nativeQuery = true
    )
    fun findAllSubCategoriesByDepth(@Param("parentId") parentId: Long, @Param("depth") depth: Int): List<Category>

    fun findByName(name: String): Optional<Category>
}
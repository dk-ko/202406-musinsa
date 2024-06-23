package com.musinsa.shop.category.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

interface CategoryProductMappingRepository: JpaRepository<CategoryProductMapping, Long> {
    fun deleteAllByProductId(id: Long)
    fun findAllByProductIdIn(ids: Collection<Long>): List<CategoryProductMapping>

    @Transactional
    @Modifying
    @Query("""
        UPDATE category_product_mapping cpm 
        SET cpm.category_id = :toUpdate
        WHERE cpm.product_id = :productId
        AND cpm.category_id = :categoryId
    """, nativeQuery = true)
    fun updateCategoryProductMapping(
        @Param("categoryId") existingCategoryId: Long,
        @Param("productId") productId: Long,
        @Param("toUpdate") categoryIdToUpdate: Long,
    ): Int
}
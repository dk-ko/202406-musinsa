package com.musinsa.shop.product.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface ProductRepository: JpaRepository<Product, Long> {
    @Query("""
        SELECT p.* FROM product p
        JOIN category_product_mapping cpm ON cpm.product_id = p.product_id
        WHERE cpm.category_id = :category_id
        ORDER BY price ASC
    """, nativeQuery = true)
    fun findByCategoryOrderByPriceAsc(@Param("category_id") categoryId: Long): List<Product>

    @Query("""
        SELECT p.* FROM product p
        JOIN category_product_mapping cpm ON cpm.product_id = p.product_id
        WHERE cpm.category_id = :category_id
        ORDER BY price ASC
        LIMIT 1
    """, nativeQuery = true)
    fun findByCategoryOrderByPriceAscLimit(@Param("category_id") categoryId: Long): Product

    @Transactional
    @Modifying
    @Query("""
        UPDATE product p 
        SET p.name = :name,
            p.price = :price
        WHERE p.product_id = :id
    """, nativeQuery = true)
    fun updateProductInfo(
        @Param("id") id: Long,
        @Param("name") name: String,
        @Param("price") price: Int,
    ): Int
}
package com.musinsa.shop.product.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product, Long> {
    // product 를 category_id 로 조회해서 최저가의 브랜드를 가져옴
    @Query("""
        SELECT p.* FROM product p
        JOIN category_product_mapping cpm ON cpm.product_id = p.product_id
        WHERE cpm.category_id = :category_id
        ORDER BY price ASC
    """, nativeQuery = true)
    fun findByCategoryOrderByPriceAsc(@Param("category_id") categoryId: Long): List<Product>
}
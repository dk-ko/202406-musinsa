package com.musinsa.shop.mapping

import org.springframework.data.jpa.repository.JpaRepository

interface CategoryProductMappingRepository: JpaRepository<CategoryProductMapping, Long> {
    fun deleteAllByProductId(id: Long)
    fun findAllByProductIdIn(ids: Collection<Long>): List<CategoryProductMapping>
}
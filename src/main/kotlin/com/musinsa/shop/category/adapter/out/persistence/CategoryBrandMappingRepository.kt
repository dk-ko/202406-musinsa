package com.musinsa.shop.category.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryBrandMappingRepository: JpaRepository<CategoryBrandMapping, Long> {
    fun deleteByBrandId(brandId: Long)
}
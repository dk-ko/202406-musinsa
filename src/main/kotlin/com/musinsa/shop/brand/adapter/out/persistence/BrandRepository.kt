package com.musinsa.shop.brand.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BrandRepository: JpaRepository<Brand, Long> {
    fun findByName(brandName: String): Optional<Brand>
    fun findByCode(code: String): Optional<Brand>
}
package com.musinsa.shop.brand.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BrandRepository: JpaRepository<Brand, Long> {
    fun findByCode(code: String): Optional<Brand>
}
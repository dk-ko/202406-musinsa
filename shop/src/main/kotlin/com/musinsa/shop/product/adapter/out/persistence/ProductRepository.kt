package com.musinsa.shop.product.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product, Long> {
}
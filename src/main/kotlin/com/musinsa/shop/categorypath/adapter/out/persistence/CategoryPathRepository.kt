package com.musinsa.shop.categorypath.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface CategoryPathRepository: JpaRepository<CategoryPath, CategoryPathId> {
}
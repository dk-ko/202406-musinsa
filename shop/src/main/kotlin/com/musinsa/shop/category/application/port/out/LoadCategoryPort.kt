package com.musinsa.shop.category.application.port.out

import com.musinsa.shop.category.adapter.out.persistence.Category

interface LoadCategoryPort {
    fun createCategory(name: String): Category
}
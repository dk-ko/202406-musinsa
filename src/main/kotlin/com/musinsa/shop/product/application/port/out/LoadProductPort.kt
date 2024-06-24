package com.musinsa.shop.product.application.port.out

import com.musinsa.shop.product.adapter.out.persistence.Product

interface LoadProductPort {
    fun createProduct(name: String, price: Int, brandCode: String, categoryIds: List<Long>): Product
    fun updateProductInfo(id: Long, name: String, price: Int): Int
    fun updateCategoryOfProduct(id: Long, existingCategoryId: Long, categoryIdToUpdate: Long): Int
    fun deleteProduct(id: Long)
    fun findByCategoryOrderByPriceAsc(categoryId: Long): List<Product>
    fun findByCategoryOrderByPriceAscLimit(categoryId: Long): Product
}
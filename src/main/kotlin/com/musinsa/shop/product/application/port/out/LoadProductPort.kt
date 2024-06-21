package com.musinsa.shop.product.application.port.out

import com.musinsa.shop.product.adapter.`in`.web.CategoryPriceSummaryDto
import com.musinsa.shop.product.adapter.out.persistence.Product

interface LoadProductPort {
    fun createProduct(name: String, price: Int, brandCode: String, categoryIds: List<Long>): Product
    fun updateProduct(name: String, price: Int, brandCode: String, categoryIds: List<Long>): Product
    fun deleteProduct(id: Long)
    fun getLowestPricedBrandByCategory(): CategoryPriceSummaryDto
}
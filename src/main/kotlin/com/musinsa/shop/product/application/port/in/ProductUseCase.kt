package com.musinsa.shop.product.application.port.`in`

import com.musinsa.shop.product.adapter.`in`.web.CategoryPriceSummaryDto
import com.musinsa.shop.product.adapter.`in`.web.CheapestPricesDto
import com.musinsa.shop.product.adapter.`in`.web.MinMaxPriceBrandDto
import com.musinsa.shop.product.adapter.`in`.web.ProductResDto

interface ProductUseCase {
    fun getLowestPricedBrandByCategory(): CategoryPriceSummaryDto
    fun getCheapestBrandAndCategoryPrices(): CheapestPricesDto
    fun getBrandsWithPriceExtremesByCategory(name: String): MinMaxPriceBrandDto
    fun createProduct(name: String, price: Int, brandCode: String, categoryIds: List<Long>): ProductResDto
    fun updateProductInfo(id: Long, name: String, price: Int): Int
    fun updateCategoryOfProduct(id: Long, existingCategoryId: Long, categoryIdToUpdate: Long): Int
    fun deleteProduct(id: Long)
}
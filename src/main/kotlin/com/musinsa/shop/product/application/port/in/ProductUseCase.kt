package com.musinsa.shop.product.application.port.`in`

import com.musinsa.shop.product.adapter.`in`.web.CategoryPriceSummaryDto
import com.musinsa.shop.product.adapter.`in`.web.CheapestPricesDto
import com.musinsa.shop.product.adapter.`in`.web.MinMaxPriceBrandDto

interface ProductUseCase {
    fun getLowestPricedBrandByCategory(): CategoryPriceSummaryDto
    fun getCheapestBrandAndCategoryPrices(): CheapestPricesDto
    fun getBrandsWithPriceExtremesByCategory(name: String): MinMaxPriceBrandDto
}
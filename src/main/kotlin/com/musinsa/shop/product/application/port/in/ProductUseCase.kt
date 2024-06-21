package com.musinsa.shop.product.application.port.`in`

import com.musinsa.shop.brand.adapter.`in`.web.CategoryPriceSummaryDto

interface ProductUseCase {
    fun getLowestPricedBrandByCategory(): CategoryPriceSummaryDto
}
package com.musinsa.shop.product.application.port.`in`

import com.musinsa.shop.product.adapter.`in`.web.CategoryPriceSummaryDto

interface ProductUseCase {
    fun getLowestPricedBrandByCategory(): CategoryPriceSummaryDto
}
package com.musinsa.shop.product.adapter.`in`.web

data class CategoryPriceSummaryDto(
    val categoryPriceDetails: List<CategoryPriceDetailDto>,
    val totalLowestPrice: Int,
)
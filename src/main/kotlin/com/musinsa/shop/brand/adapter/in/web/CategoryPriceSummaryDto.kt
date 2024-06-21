package com.musinsa.shop.brand.adapter.`in`.web

data class CategoryPriceSummaryDto(
    val categoryPriceDetails: List<CategoryPriceDetailDto>,
    val totalLowestPrice: Int,
)
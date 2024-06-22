package com.musinsa.shop.product.adapter.`in`.web

data class CategoryPriceSummaryDto(
    val categoryPriceDetails: List<CategoryPriceDetailDto>,
    val totalLowestPrice: Int,
)

data class CategoryPriceDetailDto(
    val categoryName: String,
    val brandName: String,
    val lowestPrice: Int,
)
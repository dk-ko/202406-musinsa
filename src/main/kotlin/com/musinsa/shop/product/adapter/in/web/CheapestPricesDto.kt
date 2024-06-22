package com.musinsa.shop.product.adapter.`in`.web

data class CategoryPriceDto(
    val category: String,
    val price: Int
)

data class BrandPricingDto(
    val brand: String,
    val categories: List<CategoryPriceDto>,
    val total: Int
)

data class CheapestPricesDto(
    val brandPricingDto: BrandPricingDto
)
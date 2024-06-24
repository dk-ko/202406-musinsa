package com.musinsa.shop.product.adapter.`in`.web

data class MinMaxPriceBrandDto(
    val category: String,
    val minPrice: PriceBrandDto,
    val maxPrice: PriceBrandDto,
)

data class PriceBrandDto(
    val brand: String,
    val price: Int,
)
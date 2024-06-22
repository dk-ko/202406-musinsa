package com.musinsa.shop.product.adapter.`in`.web

data class MinMaxPriceBrandDto(
    val category: String,
    val minPrice: MinPriceBrandDto,
    val maxPrice: MaxPriceBrandDto,
)

data class MinPriceBrandDto(
    val brand: String,
    val price: Int,
)

data class MaxPriceBrandDto(
    val brand: String,
    val price: Int,
)
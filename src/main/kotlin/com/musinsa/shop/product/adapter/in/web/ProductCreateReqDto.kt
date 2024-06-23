package com.musinsa.shop.product.adapter.`in`.web

import org.jetbrains.annotations.NotNull

data class ProductCreateReqDto(
    @NotNull
    val name: String,
    @NotNull
    val price: Int,
    @NotNull
    val brandCode: String,
    @NotNull
    val categoryIds: List<Long>
)
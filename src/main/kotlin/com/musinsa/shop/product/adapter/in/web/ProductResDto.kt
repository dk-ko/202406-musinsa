package com.musinsa.shop.product.adapter.`in`.web

import org.jetbrains.annotations.NotNull

class ProductResDto(
    @NotNull
    val id: Long,
    @NotNull
    val name: String,
    @NotNull
    val price: Int,
    @NotNull
    val brandCode: String,
)
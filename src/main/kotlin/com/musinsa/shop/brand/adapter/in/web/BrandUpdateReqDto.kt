package com.musinsa.shop.brand.adapter.`in`.web

import org.jetbrains.annotations.NotNull

data class BrandUpdateReqDto(
    @NotNull
    val id: Long,
    @NotNull
    val name: String,
    @NotNull
    val code: String,
)
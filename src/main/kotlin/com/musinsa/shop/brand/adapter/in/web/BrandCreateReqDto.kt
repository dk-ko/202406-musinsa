package com.musinsa.shop.brand.adapter.`in`.web

import org.jetbrains.annotations.NotNull

data class BrandCreateReqDto(
    @NotNull val name: String,
    @NotNull val code: String,
)
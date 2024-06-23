package com.musinsa.shop.product.adapter.`in`.web

data class CategoryOfProductReqDto(
    val productId: Long,
    val existingCategoryId: Long,
    val categoryIdToUpdate: Long,
)
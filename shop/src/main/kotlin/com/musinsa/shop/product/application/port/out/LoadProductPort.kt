package com.musinsa.shop.product.application.port.out

import com.musinsa.shop.product.adapter.out.persistence.Product

interface LoadProductPort {
    fun createProduct(name: String, price: Int, brandCode: String): Product
}
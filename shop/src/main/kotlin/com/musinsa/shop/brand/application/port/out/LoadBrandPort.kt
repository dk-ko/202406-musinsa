package com.musinsa.shop.brand.application.port.out

import com.musinsa.shop.brand.adapter.out.persistence.Brand

interface LoadBrandPort {
    fun createBrand(name: String, code: String): Brand
    fun getBrandByCode(brandCode: String): Brand
}
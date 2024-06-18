package com.musinsa.shop.product.adapter.out.persistence

import com.musinsa.shop.brand.application.port.out.LoadBrandPort
import com.musinsa.shop.product.application.port.out.LoadProductPort

class ProductPersistenceAdapter(
    private val productRepository: ProductRepository,
    private val loadBrandPort: LoadBrandPort,
): LoadProductPort {
    override fun createProduct(name: String, price: Int, brandCode: String): Product {
        val findBrand = loadBrandPort.getBrandByCode(brandCode)

        return productRepository.save(
            Product(
                name = name,
                price = price,
                brandCode = brandCode,
                brand = findBrand
            )
        )
    }
}
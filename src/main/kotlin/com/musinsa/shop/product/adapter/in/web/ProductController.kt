package com.musinsa.shop.product.adapter.`in`.web

import com.musinsa.shop.product.adapter.out.persistence.ProductPersistenceAdapter
import com.musinsa.shop.product.application.port.`in`.ProductUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductController(
    private val productUseCase: ProductUseCase,
    private val productPersistenceAdapter: ProductPersistenceAdapter,
) {
    @GetMapping("/category-lowest-price")
    fun readLowestPriceBrandByCategory(
    ): ResponseEntity<CategoryPriceSummaryDto> {
        return ResponseEntity(productUseCase.getLowestPricedBrandByCategory(), HttpStatus.OK)
    }

    @GetMapping("/cheapest-brand-category-prices")
    fun getCheapestBrandAndCategoryPrices(): CheapestPricesDto {
        return productPersistenceAdapter.getCheapestBrandAndCategoryPrices()
    }
}
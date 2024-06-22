package com.musinsa.shop.product.adapter.`in`.web

import com.musinsa.shop.product.application.port.`in`.ProductUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(
    private val productUseCase: ProductUseCase,
) {
    @GetMapping("/category-lowest-price")
    fun readLowestPriceBrandByCategory(
    ): ResponseEntity<CategoryPriceSummaryDto> {
        return ResponseEntity(productUseCase.getLowestPricedBrandByCategory(), HttpStatus.OK)
    }

    @GetMapping("/cheapest-brand-category-prices")
    fun getCheapestBrandAndCategoryPrices(): ResponseEntity<CheapestPricesDto> {
        return ResponseEntity(productUseCase.getCheapestBrandAndCategoryPrices(), HttpStatus.OK)
    }

    @PostMapping("/category-min-max-prices")
    fun getBrandsWithPriceExtremesByCategory(
        @RequestBody categoryRequest: CategoryRequest
    ): ResponseEntity<MinMaxPriceBrandDto> {
        return ResponseEntity(productUseCase.getBrandsWithPriceExtremesByCategory(categoryRequest.name), HttpStatus.OK)
    }
}
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

    @PostMapping
    fun createProduct(
        @RequestBody productCreateReqDto: ProductCreateReqDto,
    ): ResponseEntity<ProductResDto> {
        return ResponseEntity(
            productUseCase.createProduct(
                name = productCreateReqDto.name,
                price = productCreateReqDto.price,
                brandCode = productCreateReqDto.brandCode,
                categoryIds = productCreateReqDto.categoryIds,
            ), HttpStatus.OK
        )
    }

    @PatchMapping
    fun updateProductInfo(
        @RequestBody productUpdateReqDto: ProductUpdateReqDto,
    ): ResponseEntity<Int> {
        return ResponseEntity(
            productUseCase.updateProductInfo(
                id = productUpdateReqDto.id,
                name = productUpdateReqDto.name,
                price = productUpdateReqDto.price
            ), HttpStatus.OK
        )
    }

    @PatchMapping("/category")
    fun updateCategoryOfProduct(
        @RequestBody categoryOfProductReqDto: CategoryOfProductReqDto,
    ): ResponseEntity<Int> {
        return ResponseEntity(
            productUseCase.updateCategoryOfProduct(
                id = categoryOfProductReqDto.productId,
                existingCategoryId = categoryOfProductReqDto.existingCategoryId,
                categoryIdToUpdate = categoryOfProductReqDto.categoryIdToUpdate,
            ), HttpStatus.OK
        )
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable("id") id: Long): ResponseEntity<Long> {
        productUseCase.deleteProduct(id)
        return ResponseEntity(id, HttpStatus.NO_CONTENT)
    }
}
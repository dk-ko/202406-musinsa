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
            ), HttpStatus.CREATED
        )
    }

    @PatchMapping
    fun updateProductInfo(
        @RequestParam("productId") productId: Long,
        @RequestBody productUpdateReqDto: ProductUpdateReqDto,
    ): ResponseEntity<Int> {
        return ResponseEntity(
            productUseCase.updateProductInfo(
                id = productId,
                name = productUpdateReqDto.name,
                price = productUpdateReqDto.price
            ), HttpStatus.OK
        )
    }

    @PatchMapping("/category")
    fun updateCategoryOfProduct(
        @RequestParam("productId") productId: Long,
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

    @DeleteMapping
    fun deleteProduct(@RequestParam("productId") id: Long): ResponseEntity<Long> {
        productUseCase.deleteProduct(id)
        return ResponseEntity(id, HttpStatus.NO_CONTENT)
    }
}
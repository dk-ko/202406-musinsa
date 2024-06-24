package com.musinsa.shop.product.application

import com.musinsa.shop.brand.adapter.out.persistence.Brand
import com.musinsa.shop.brand.adapter.out.persistence.BrandRepository
import com.musinsa.shop.category.adapter.out.persistence.Category
import com.musinsa.shop.category.adapter.out.persistence.CategoryRepository
import com.musinsa.shop.common.AcceptanceTest
import com.musinsa.shop.product.application.port.out.LoadProductPort
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired

class ProductServiceTest: AcceptanceTest() {
    @Autowired
    lateinit var productService: ProductService

    @Autowired
    lateinit var loadProductPort: LoadProductPort

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Autowired
    lateinit var brandRepository: BrandRepository

    @BeforeEach
    fun setup() {
        val category1 = Category(name = "Category1")
        val category2 = Category(name = "Category2")
        val savedCategory1 = categoryRepository.save(category1)
        val savedCategory2 = categoryRepository.save(category2)

        val brand1 = Brand(name = "Brand1", code = "A001")
        val brand2 = Brand(name = "Brand2", code = "B001")
        brandRepository.save(brand1)
        brandRepository.save(brand2)

        loadProductPort.createProduct(name = "Product1", price = 100, brandCode = "A001", categoryIds = listOf(savedCategory1.id!!))
        loadProductPort.createProduct(name = "Product2", price = 200, brandCode = "B001", categoryIds = listOf(savedCategory1.id!!))
        loadProductPort.createProduct(name = "Product3", price = 150, brandCode = "A001", categoryIds = listOf(savedCategory2.id!!))
        loadProductPort.createProduct(name = "Product4", price = 250, brandCode = "B001", categoryIds = listOf(savedCategory2.id!!))
    }

    @Test
    fun getLowestPricedBrandByCategory() {
        // When
        val result = productService.getLowestPricedBrandByCategory()

        // Then
        assertEquals(2, result.categoryPriceDetails.size)
        assertEquals("Category1", result.categoryPriceDetails[0].categoryName)
        assertEquals("Brand1", result.categoryPriceDetails[0].brandName)
        assertEquals(100, result.categoryPriceDetails[0].lowestPrice)
        assertEquals("Category2", result.categoryPriceDetails[1].categoryName)
        assertEquals("Brand1", result.categoryPriceDetails[1].brandName)
        assertEquals(150, result.categoryPriceDetails[1].lowestPrice)
        assertEquals(250, result.totalLowestPrice)
    }

    @Test
    fun getCheapestBrandAndCategoryPrices() {
        // When
        val result = productService.getLowestPricedBrandByCategory()

        // Then
        assertEquals(2, result.categoryPriceDetails.size)
        assertEquals("Brand1", result.categoryPriceDetails[0].brandName)
        assertEquals("Brand1", result.categoryPriceDetails[1].brandName)
        assertEquals(100, result.categoryPriceDetails[0].lowestPrice)
        assertEquals("Category1", result.categoryPriceDetails[0].categoryName)
        assertEquals(150, result.categoryPriceDetails[1].lowestPrice)
        assertEquals("Category2", result.categoryPriceDetails[1].categoryName)
        assertEquals(250, result.totalLowestPrice)
    }

    @Test
    fun getBrandsWithPriceExtremesByCategory() {
        // When
        val result = productService.getBrandsWithPriceExtremesByCategory("Category1")

        // then
        assertEquals(100, result.minPrice.price)
        assertEquals("Brand1", result.minPrice.brand)
        assertEquals(200, result.maxPrice.price)
        assertEquals("Brand2", result.maxPrice.brand)
    }
}
package com.musinsa.shop.product.adapter.out.persistence

import com.musinsa.shop.brand.adapter.out.persistence.BrandPersistenceAdapter
import com.musinsa.shop.category.application.port.`in`.CategoryUseCase
import com.musinsa.shop.common.AcceptanceTest
import com.musinsa.shop.category.adapter.out.persistence.CategoryProductMappingRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ProductPersistenceAdapterTest: AcceptanceTest() {
    @Autowired
    lateinit var productPersistenceAdapter: ProductPersistenceAdapter

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var brandPersistenceAdapter: BrandPersistenceAdapter

    @Autowired
    lateinit var categoryProductMappingRepository: CategoryProductMappingRepository

    @Autowired
    lateinit var categoryUseCase: CategoryUseCase

    @Test
    fun `상품 생성 테스트`() {
        val rootCategory = categoryUseCase.createRootCategory("상의")
        val subCategory = categoryUseCase.addSubCategory(rootCategory.id!!, "반팔티")
        val createBrand = brandPersistenceAdapter.createBrand("ABC", "TEST")

        val createProduct = productPersistenceAdapter.createProduct(
            name = "Test product",
            price = 10_000,
            brandCode = createBrand.code,
            listOf(rootCategory.id!!, subCategory.id!!)
        )

        val root = createProduct.categoryMappings[0].category.id
        assertThat(root).isEqualTo(rootCategory.id)
        val sub = createProduct.categoryMappings[1].category.id
        assertThat(sub).isEqualTo(subCategory.id)
        assertThat(createProduct.brand.id).isEqualTo(createBrand.id)
        assertThat(categoryProductMappingRepository.findAllByProductIdIn(listOf(createProduct.id!!))).isNotEmpty
    }

    @Test
    fun `상품 수정 테스트`() {
        val rootCategory = categoryUseCase.createRootCategory("상의")
        val subCategory = categoryUseCase.addSubCategory(rootCategory.id!!, "반팔티")
        val createBrand = brandPersistenceAdapter.createBrand("ABC", "TEST")

        productPersistenceAdapter.createProduct(
            name = "Test product",
            price = 10_000,
            brandCode = createBrand.code,
            listOf(rootCategory.id!!, subCategory.id!!)
        )

        val anotherRootCategory = categoryUseCase.createRootCategory("하이")
        val anotherSubCategory = categoryUseCase.addSubCategory(rootCategory.id!!, "반바지")
        val anotherCreateBrand = brandPersistenceAdapter.createBrand("DEF", "TEST 2")

        val updateProduct = productPersistenceAdapter.updateProduct(
            name = "Test product2",
            price = 10_000,
            brandCode = anotherCreateBrand.code,
            listOf(anotherRootCategory.id!!, anotherSubCategory.id!!)
        )

        val root = updateProduct.categoryMappings[0].category.id
        assertThat(root).isEqualTo(anotherRootCategory.id)
        val sub = updateProduct.categoryMappings[1].category.id
        assertThat(sub).isEqualTo(anotherSubCategory.id)
        assertThat(updateProduct.brand.id).isEqualTo(anotherCreateBrand.id)
        assertThat(categoryProductMappingRepository.findAllByProductIdIn(listOf(updateProduct.id!!))).isNotEmpty
    }

    @Test
    fun `상품 삭제 테스트`() {
        val rootCategory = categoryUseCase.createRootCategory("상의")
        val subCategory = categoryUseCase.addSubCategory(rootCategory.id!!, "반팔티")
        val createBrand = brandPersistenceAdapter.createBrand("ABC", "TEST")

        val createProduct = productPersistenceAdapter.createProduct(
            name = "Test product",
            price = 10_000,
            brandCode = createBrand.code,
            listOf(rootCategory.id!!, subCategory.id!!)
        )

        productPersistenceAdapter.deleteProduct(createProduct.id!!)

        assertThat(productRepository.findById(createProduct.id!!)).isEmpty
        assertThat(categoryProductMappingRepository.findAllByProductIdIn(listOf(createProduct.id!!))).isEmpty()
    }
}
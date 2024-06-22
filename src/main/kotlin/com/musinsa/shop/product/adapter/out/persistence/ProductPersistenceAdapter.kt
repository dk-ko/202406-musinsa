package com.musinsa.shop.product.adapter.out.persistence

import com.musinsa.shop.brand.application.port.out.LoadBrandPort
import com.musinsa.shop.category.adapter.out.persistence.CategoryPersistenceAdapter
import com.musinsa.shop.category.adapter.out.persistence.CategoryRepository
import com.musinsa.shop.category.application.port.out.LoadCategoryPort
import com.musinsa.shop.mapping.CategoryProductMappingRepository
import com.musinsa.shop.product.adapter.`in`.web.*
import com.musinsa.shop.product.application.port.out.LoadProductPort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ProductPersistenceAdapter(
    private val productRepository: ProductRepository,
    private val categoryProductMappingRepository: CategoryProductMappingRepository,
    private val loadBrandPort: LoadBrandPort,
    private val loadCategoryPort: LoadCategoryPort,
    private val categoryPersistenceAdapter: CategoryPersistenceAdapter,
    private val categoryRepository: CategoryRepository,
): LoadProductPort {
    @Transactional
    override fun createProduct(name: String, price: Int, brandCode: String, categoryIds: List<Long>): Product {
        return saveProduct(brandCode, categoryIds, name, price)
    }

    @Transactional
    override fun updateProduct(name: String, price: Int, brandCode: String, categoryIds: List<Long>): Product {
        return saveProduct(brandCode, categoryIds, name, price)
    }

    private fun saveProduct(
        brandCode: String,
        categoryIds: List<Long>,
        name: String,
        price: Int
    ): Product {
        val findBrand = loadBrandPort.getBrandByCode(brandCode)
        val findCategories = loadCategoryPort.findCategoriesByIds(categoryIds)

        val savedProduct = productRepository.save(
            Product(
                name = name,
                price = price,
                brandCode = brandCode,
                brand = findBrand,
            )
        )

        savedProduct.addAllCategory(findCategories)

        return savedProduct
    }

    @Transactional
    override fun deleteProduct(id: Long) {
        productRepository.deleteById(id)
        categoryProductMappingRepository.deleteAllByProductId(id)
    }

    @Transactional
    override fun getLowestPricedBrandByCategory(): CategoryPriceSummaryDto {
        val categories = loadCategoryPort.getAllCategories()
        val categoryPriceDetails = mutableListOf<CategoryPriceDetailDto>()

        var totalLowestPrice = 0

        for (category in categories) {
            val lowestPricedProduct = productRepository.findByCategoryOrderByPriceAsc(category.id!!).first()
            if (lowestPricedProduct != null) {
                val categoryPriceDetail = CategoryPriceDetailDto(
                    categoryName = category.name,
                    brandName = lowestPricedProduct.brand.name,
                    lowestPrice = lowestPricedProduct.price
                )
                categoryPriceDetails.add(categoryPriceDetail)
                totalLowestPrice += lowestPricedProduct.price
                // TODO 캐싱 등 성능 개선 포인트 고민
            }
        }

        return CategoryPriceSummaryDto(
            categoryPriceDetails = categoryPriceDetails,
            totalLowestPrice = totalLowestPrice
        )
    }

    fun getCheapestBrandAndCategoryPrices(): CheapestPricesDto {
        val categories = categoryPersistenceAdapter.getAllCategories()
        val brandPrices = mutableMapOf<String, MutableList<CategoryPriceDto>>()
        val brandTotalPrices = mutableMapOf<String, Int>()

        for (category in categories) {
            val products = productRepository.findByCategoryOrderByPriceAsc(category.id!!)
            products.map { product ->
                val brand = product.brand.name
                val price = product.price

                brandPrices.computeIfAbsent(brand) { mutableListOf() }.add(CategoryPriceDto(category.name, price))
                brandTotalPrices[brand] = brandTotalPrices.getOrDefault(brand, 0) + price
            }
        }

        val bestBrand = brandTotalPrices.minByOrNull { it.value }?.key ?: throw IllegalStateException("No products found")
        val categoriesWithPrices = brandPrices[bestBrand] ?: throw IllegalStateException("No products found for brand")

        return CheapestPricesDto(
            BrandPricingDto(brand = bestBrand, categories = categoriesWithPrices, total = brandTotalPrices[bestBrand]!!)
        )
    }

    fun getBrandsWithPriceExtremesByCategory(name: String): MinMaxPriceBrandDto {
        val category = categoryPersistenceAdapter.findByName(name)
        val products = productRepository.findByCategoryOrderByPriceAsc(category.id!!)
        val lowestPriceProduct = products.first()
        val highestPriceProduct = products.last()

        val lowestPriceBrand = loadBrandPort.getBrandByCode(lowestPriceProduct.brandCode)
        val highestPriceBrand = loadBrandPort.getBrandByCode(highestPriceProduct.brandCode)

        return MinMaxPriceBrandDto(
            category = name,
            MinPriceBrandDto(
                lowestPriceBrand.name,
                lowestPriceProduct.price,
            ),
            MaxPriceBrandDto(
                highestPriceBrand.name,
                highestPriceProduct.price,
            )
        )
    }
}
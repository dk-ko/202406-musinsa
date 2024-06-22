package com.musinsa.shop.product.application

import com.musinsa.shop.brand.application.port.out.LoadBrandPort
import com.musinsa.shop.category.application.port.out.LoadCategoryPort
import com.musinsa.shop.product.adapter.`in`.web.*
import com.musinsa.shop.product.application.port.`in`.ProductUseCase
import com.musinsa.shop.product.application.port.out.LoadProductPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val loadProductPort: LoadProductPort,
    private val loadCategoryPort: LoadCategoryPort,
    private val loadBrandPort: LoadBrandPort,
): ProductUseCase {
    @Transactional
    override fun getLowestPricedBrandByCategory(): CategoryPriceSummaryDto {
        val categories = loadCategoryPort.getAllCategories()
        val categoryPriceDetails = mutableListOf<CategoryPriceDetailDto>()

        var totalLowestPrice = 0

        for (category in categories) {
            val lowestPricedProduct = loadProductPort.findByCategoryOrderByPriceAsc(category.id!!).first()
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

    @Transactional
    override fun getCheapestBrandAndCategoryPrices(): CheapestPricesDto {
        val categories = loadCategoryPort.getAllCategories()
        val brandPrices = mutableMapOf<String, MutableList<CategoryPriceDto>>()
        val brandTotalPrices = mutableMapOf<String, Int>()

        for (category in categories) {
            val products = loadProductPort.findByCategoryOrderByPriceAsc(category.id!!)
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

    @Transactional
    override fun getBrandsWithPriceExtremesByCategory(name: String): MinMaxPriceBrandDto {
        val category = loadCategoryPort.findByName(name)
        val products = loadProductPort.findByCategoryOrderByPriceAsc(category.id!!)
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
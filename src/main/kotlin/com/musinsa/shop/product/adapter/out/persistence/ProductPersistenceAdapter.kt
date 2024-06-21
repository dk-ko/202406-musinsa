package com.musinsa.shop.product.adapter.out.persistence

import com.musinsa.shop.product.adapter.`in`.web.CategoryPriceDetailDto
import com.musinsa.shop.product.adapter.`in`.web.CategoryPriceSummaryDto
import com.musinsa.shop.brand.application.port.out.LoadBrandPort
import com.musinsa.shop.category.application.port.out.LoadCategoryPort
import com.musinsa.shop.mapping.CategoryProductMappingRepository
import com.musinsa.shop.product.application.port.out.LoadProductPort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ProductPersistenceAdapter(
    private val productRepository: ProductRepository,
    private val categoryProductMappingRepository: CategoryProductMappingRepository,
    private val loadBrandPort: LoadBrandPort,
    private val loadCategoryPort: LoadCategoryPort,
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
            }
        }

        return CategoryPriceSummaryDto(
            categoryPriceDetails = categoryPriceDetails,
            totalLowestPrice = totalLowestPrice
        )
    }
}
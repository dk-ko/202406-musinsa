package com.musinsa.shop.product.adapter.out.persistence

import com.musinsa.shop.brand.application.port.out.LoadBrandPort
import com.musinsa.shop.category.adapter.out.persistence.CategoryProductMapping
import com.musinsa.shop.category.adapter.out.persistence.CategoryProductMappingRepository
import com.musinsa.shop.category.application.port.out.LoadCategoryPort
import com.musinsa.shop.category.application.port.out.LoadCategoryProductMappingPort
import com.musinsa.shop.exception.NotFoundException
import com.musinsa.shop.product.application.port.out.LoadProductPort
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ProductPersistenceAdapter(
    private val productRepository: ProductRepository,
    private val categoryProductMappingRepository: CategoryProductMappingRepository,

    private val loadBrandPort: LoadBrandPort,
    private val loadCategoryPort: LoadCategoryPort,
    private val loadCategoryProductMappingPort: LoadCategoryProductMappingPort,
): LoadProductPort {
    @Transactional
    override fun createProduct(name: String, price: Int, brandCode: String, categoryIds: List<Long>): Product {
        val findBrand = loadBrandPort.getBrandByCode(brandCode)
        val findCategories = loadCategoryPort.findCategoriesByIds(categoryIds)

        if (findCategories.isEmpty()) {
            throw NotFoundException("$categoryIds not found")
        }

        val savedProduct = productRepository.save(
            Product(
                name = name,
                price = price,
                brandCode = brandCode,
                brand = findBrand,
            )
        )

        val categoryProductMappingList = findCategories.map { category ->
            CategoryProductMapping(
                id = null,
                category = category,
                product = savedProduct,
            )
        }

        categoryProductMappingList.forEach {
            loadCategoryProductMappingPort.createCategoryProductMapping(it)
        }

        return savedProduct
    }

    @Transactional
    override fun updateProductInfo(id: Long, name: String, price: Int): Int {
        this.findById(id)

        return productRepository.updateProductInfo(
            id = id,
            name = name,
            price = price,
        )
    }

    @Transactional
    override fun updateCategoryOfProduct(id: Long, existingCategoryId: Long, categoryIdToUpdate: Long): Int {
        loadCategoryPort.findCategoryById(existingCategoryId)
        loadCategoryPort.findCategoryById(categoryIdToUpdate)

        return loadCategoryProductMappingPort.updateCategoryProductMapping(
            productId = id,
            existingCategoryId = existingCategoryId,
            categoryIdToUpdate = categoryIdToUpdate,
        )
    }

    @Transactional
    override fun deleteProduct(id: Long) {
        categoryProductMappingRepository.deleteAllByProductId(id)
        productRepository.deleteById(id)
    }

    @Transactional(readOnly = true)
    override fun findByCategoryOrderByPriceAsc(categoryId: Long): List<Product> {
        return productRepository.findByCategoryOrderByPriceAsc(categoryId)
    }

    @Transactional(readOnly = true)
    override fun findByCategoryOrderByPriceAscLimit(categoryId: Long): Product {
        return productRepository.findByCategoryOrderByPriceAscLimit(categoryId)
    }

    private fun findById(id: Long): Product {
        return productRepository.findById(id).orElseThrow {
            throw NotFoundException("$id not found")
        }
    }
}
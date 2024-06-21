package com.musinsa.shop.product.application

import com.musinsa.shop.product.adapter.`in`.web.CategoryPriceSummaryDto
import com.musinsa.shop.product.application.port.`in`.ProductUseCase
import com.musinsa.shop.product.application.port.out.LoadProductPort
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val loadProductPort: LoadProductPort,
): ProductUseCase {
    override fun getLowestPricedBrandByCategory(): CategoryPriceSummaryDto {
        return loadProductPort.getLowestPricedBrandByCategory()
    }
}
package com.musinsa.shop.brand.adapter.out.persistence

import com.musinsa.shop.brand.application.port.out.LoadBrandPort
import com.musinsa.shop.exception.NotFoundException
import kotlin.jvm.optionals.getOrElse

class BrandPersistenceAdapter(
    private val brandRepository: BrandRepository,
) : LoadBrandPort {
    override fun createBrand(name: String, code: String): Brand {
        return brandRepository.save(
            Brand(
                name = name,
                code = code,
            )
        )
    }

    override fun getBrandByCode(brandCode: String): Brand {
        return brandRepository.findByCode(brandCode).getOrElse {
            throw NotFoundException("$brandCode not found")
        }
    }
}
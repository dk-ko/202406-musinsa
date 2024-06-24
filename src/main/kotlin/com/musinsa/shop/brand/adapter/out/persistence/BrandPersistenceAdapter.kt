package com.musinsa.shop.brand.adapter.out.persistence

import com.musinsa.shop.brand.application.port.out.LoadBrandPort
import com.musinsa.shop.exception.DuplicateException
import com.musinsa.shop.exception.NotFoundException
import org.springframework.stereotype.Component
import kotlin.jvm.optionals.getOrElse

@Component
class BrandPersistenceAdapter(
    private val brandRepository: BrandRepository,
) : LoadBrandPort {
    override fun createBrand(name: String, code: String): Brand {
        checkDuplicate(name, code)

        return brandRepository.save(
            Brand(
                name = name,
                code = code,
            )
        )
    }

    private fun checkDuplicate(name: String, code: String) {
        if (brandRepository.findByNameOrCode(brandName = name, code = code).isPresent) {
            throw DuplicateException("Resource with name '${name}' and code '${code}' already exists")
        }
    }

    override fun getBrandByCode(brandCode: String): Brand {
        return brandRepository.findByCode(brandCode).getOrElse {
            throw NotFoundException("$brandCode not found")
        }
    }

    override fun getBrandById(id: Long): Brand {
        return brandRepository.findById(id).getOrElse {
            throw NotFoundException("$id not found")
        }
    }

    override fun updateBrand(id: Long, name: String, code: String): Int {
        checkDuplicate(name, code)

        return brandRepository.updateNameAndCodeById(name, code, id)
    }

    override fun deleteBrand(id: Long) {
        brandRepository.deleteById(id)
    }
}
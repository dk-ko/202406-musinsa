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
        // TODO 쿼리 한번 호출로 변경
        if (brandRepository.findByName(name).isPresent) {
            throw DuplicateException("Brand with name $name already exists")
        }

        if (brandRepository.findByCode(code).isPresent) {
            throw DuplicateException("Brand with code $code already exists")
        }
    }

    // TODO ID로 조회 또는 INDEX
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

    override fun updateBrand(id: Long, name: String, code: String): Brand {
        checkDuplicate(name, code)

        val brand = this.getBrandById(id)
        brand.updateName(name)
        brand.updateCode(code)

        return brandRepository.updateNameAndCodeById(brand.name, brand.code, brand.id!!)
    }

    override fun deleteBrand(id: Long) {
        brandRepository.deleteById(id)
    }
}
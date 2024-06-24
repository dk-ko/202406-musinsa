package com.musinsa.shop.brand.adapter.out.persistence

import com.musinsa.shop.common.AcceptanceTest
import com.musinsa.shop.exception.NotFoundException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired

class BrandPersistenceAdapterTest: AcceptanceTest() {
    @Autowired
    lateinit var brandPersistenceAdapter: BrandPersistenceAdapter

    @Autowired
    lateinit var brandRepository: BrandRepository

    @Test
    fun createBrand() {
        // given
        val createBrand = brandPersistenceAdapter.createBrand("brand", "code")

        // when
        val findBrand = brandRepository.findById(createBrand.id!!)

        // then
        assertThat(findBrand).isNotEmpty
    }

    @Test
    fun getBrandByCode() {
        // given
        val code = "code"
        val createBrand = brandPersistenceAdapter.createBrand("brand", code)

        // when
        val brandByCode = brandPersistenceAdapter.getBrandByCode(code)

        // then
        assertThat(createBrand.id).isEqualTo(brandByCode.id)
    }

    @Test
    fun getBrandById() {
        // given
        val createBrand = brandPersistenceAdapter.createBrand("brand", "code")

        // when
        val brandById = brandPersistenceAdapter.getBrandById(createBrand.id!!)

        // then
        assertThat(createBrand.id).isEqualTo(brandById.id)
    }

    @Test
    fun updateBrand() {
        // given
        val createBrand = brandPersistenceAdapter.createBrand("brand", "code")

        // when
        val updateResult = brandPersistenceAdapter.updateBrand(createBrand.id!!, "update", "update")

        // then
        assertThat(updateResult).isEqualTo(1)
    }

    @Test
    fun deleteBrand() {
        // given
        val createBrand = brandPersistenceAdapter.createBrand("brand", "code")

        // when
        brandPersistenceAdapter.deleteBrand(createBrand.id!!)

        // then
        assertThrows<NotFoundException> {
            brandPersistenceAdapter.getBrandById(createBrand.id!!)
        }
    }
}
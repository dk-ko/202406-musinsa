package com.musinsa.shop.brand.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface BrandRepository : JpaRepository<Brand, Long> {
    fun findByName(brandName: String): Optional<Brand>
    fun findByCode(code: String): Optional<Brand>

    @Transactional
    @Modifying
    @Query(
        """
        UPDATE Brand b 
        SET b.name = :name
        AND b.code = :code
        WHERE b.id = :id
    """, nativeQuery = true
    )
    fun updateNameAndCodeById(@Param("name") name: String, @Param("code") code: String, @Param("id") id: Long, ): Brand
}
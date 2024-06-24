package com.musinsa.shop.product.adapter.out.persistence

import com.musinsa.shop.brand.adapter.out.persistence.Brand
import com.musinsa.shop.category.adapter.out.persistence.Category
import com.musinsa.shop.common.BaseEntity
import com.musinsa.shop.category.adapter.out.persistence.CategoryProductMapping
import com.musinsa.shop.product.adapter.`in`.web.ProductResDto
import jakarta.persistence.*

@Entity
@Table(name = "product")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "price", nullable = false)
    val price: Int,

    @Column(name = "brand_code", nullable = false)
    val brandCode: String,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id", nullable = false)
    val brand: Brand

): BaseEntity() {
    fun toResponse(): ProductResDto {
        return ProductResDto(
            id = this.id!!,
            name = this.name,
            price = this.price,
            brandCode = this.brandCode,
        )
    }
}
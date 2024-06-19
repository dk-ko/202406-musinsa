package com.musinsa.shop.product.adapter.out.persistence

import com.musinsa.shop.brand.adapter.out.persistence.Brand
import com.musinsa.shop.common.BaseEntity
import com.musinsa.shop.mapping.CategoryProductMapping
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

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    val brand: Brand,

    @OneToMany(mappedBy = "product")
    val categoryMappings: Set<CategoryProductMapping> = emptySet()
): BaseEntity()
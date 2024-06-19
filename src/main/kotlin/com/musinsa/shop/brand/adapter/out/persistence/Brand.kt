package com.musinsa.shop.brand.adapter.out.persistence

import com.musinsa.shop.common.BaseEntity
import com.musinsa.shop.mapping.CategoryBrandMapping
import com.musinsa.shop.product.adapter.out.persistence.Product
import jakarta.persistence.*


@Entity
@Table(name = "brand")
class Brand(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id")
    val id: Long? = null,

    @Column(name = "name", nullable = false, unique = true)
    val name: String,

    @Column(name = "code", nullable = false, unique = true)
    val code: String,

    @OneToMany(mappedBy = "brand")
    val products: Set<Product> = emptySet(),

    @OneToMany(mappedBy = "brand")
    val categoryMappings: Set<CategoryBrandMapping> = emptySet()
): BaseEntity()
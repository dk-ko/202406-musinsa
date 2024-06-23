package com.musinsa.shop.category.adapter.out.persistence

import com.musinsa.shop.brand.adapter.out.persistence.Brand
import com.musinsa.shop.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "category_brand_mapping")
class CategoryBrandMapping(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_brand_mapping_id")
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    val category: Category,

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    val brand: Brand
): BaseEntity()
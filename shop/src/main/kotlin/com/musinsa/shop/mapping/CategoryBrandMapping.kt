package com.musinsa.shop.mapping

import com.musinsa.shop.brand.adapter.out.persistence.Brand
import com.musinsa.shop.category.adapter.out.persistence.Category
import com.musinsa.shop.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "category_brand_mapping")
data class CategoryBrandMapping(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_brand_mapping_id")
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    val category: Category,

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    val brand: Brand
): BaseEntity()
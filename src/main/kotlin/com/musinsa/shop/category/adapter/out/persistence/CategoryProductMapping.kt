package com.musinsa.shop.category.adapter.out.persistence

import com.musinsa.shop.common.BaseEntity
import com.musinsa.shop.product.adapter.out.persistence.Product
import jakarta.persistence.*

@Entity
@Table(name = "category_product_mapping")
class CategoryProductMapping(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_product_mapping_id")
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    val category: Category,

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    val product: Product
): BaseEntity()
package com.musinsa.shop.mapping

import com.musinsa.shop.category.adapter.out.persistence.Category
import com.musinsa.shop.product.adapter.out.persistence.Product
import jakarta.persistence.*

@Entity
@Table(name = "category_product_mapping")
data class CategoryProductMapping(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_product_mapping_id")
    val id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    val category: Category,

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    val product: Product
)
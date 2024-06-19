package com.musinsa.shop.category.adapter.out.persistence

import com.musinsa.shop.common.BaseEntity
import com.musinsa.shop.mapping.CategoryBrandMapping
import com.musinsa.shop.mapping.CategoryProductMapping
import jakarta.persistence.*

@Entity
@Table(name = "category")
class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    val id: Long? = null,

    @Column(name = "name", nullable = false, unique = true)
    val name: String,

    @OneToMany(mappedBy = "category")
    val productMappings: Set<CategoryProductMapping> = emptySet(),

    @OneToMany(mappedBy = "category")
    val brandMappings: Set<CategoryBrandMapping> = emptySet()
): BaseEntity() {
    fun copy(id: Long): Category {
        return Category(id, name, productMappings, brandMappings)
    }
}
package com.musinsa.shop.category.adapter.out.persistence

import com.musinsa.shop.common.BaseEntity
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
    val productMappings: MutableList<CategoryProductMapping> = mutableListOf(),

    @OneToMany(mappedBy = "category")
    val brandMappings: MutableList<CategoryBrandMapping> = mutableListOf()
): BaseEntity() {
    fun addAll(categoryProductMappingList: List<CategoryProductMapping>) {
        productMappings.addAll(categoryProductMappingList)
    }
}
package com.musinsa.shop.category.adapter.out.persistence

import com.musinsa.shop.category.adapter.`in`.web.CategoryResDto
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

): BaseEntity() {
    fun toResponse(): CategoryResDto {
        return CategoryResDto(
            id = id,
            name = name,
        )
    }
}
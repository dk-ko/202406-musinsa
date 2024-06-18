package com.musinsa.shop.categorypath.adapter.out.persistence

import com.musinsa.shop.category.adapter.out.persistence.Category
import jakarta.persistence.*
import java.io.Serializable


@Embeddable
data class CategoryPathId(
    @Column(name = "parent")
    val parentId: Long,

    @Column(name = "child")
    val childId: Long
) : Serializable

@Entity
@Table(name = "category_path")
data class CategoryPath(
    @EmbeddedId
    val id: CategoryPathId,

    @Column(name = "depth", nullable = false)
    val depth: Int,

    @ManyToOne
    @JoinColumn(name = "parent", insertable = false, updatable = false)
    val parent: Category,

    @ManyToOne
    @JoinColumn(name = "child", insertable = false, updatable = false)
    val child: Category
)
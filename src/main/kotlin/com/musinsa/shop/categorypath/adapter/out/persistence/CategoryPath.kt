package com.musinsa.shop.categorypath.adapter.out.persistence

import com.musinsa.shop.category.adapter.out.persistence.Category
import com.musinsa.shop.common.BaseEntity
import jakarta.persistence.*
import java.io.Serializable


@Embeddable
data class CategoryPathId(
    @Column(name = "parent_id")
    val parentId: Long,

    @Column(name = "child_id")
    val childId: Long
) : Serializable

@Entity
@Table(name = "category_path")
class CategoryPath(
    @EmbeddedId
    val id: CategoryPathId,

    @Column(name = "depth", nullable = false)
    val depth: Int,

    @ManyToOne
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    val parent: Category,

    @ManyToOne
    @JoinColumn(name = "child_id", insertable = false, updatable = false)
    val child: Category
): BaseEntity()
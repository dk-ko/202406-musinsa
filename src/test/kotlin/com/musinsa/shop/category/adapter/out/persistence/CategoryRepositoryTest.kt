package com.musinsa.shop.category.adapter.out.persistence

import com.musinsa.shop.common.AcceptanceTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

class CategoryRepositoryTest: AcceptanceTest() {
    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Autowired
    lateinit var categoryPersistenceAdapter: CategoryPersistenceAdapter

    @Test
    @Transactional
    fun `전체 루트카테고리만 조회 테스트`() {
        // given
        // root category 1, 2, 3 생성
        categoryPersistenceAdapter.createRootCategory("first")
        val secondRootCategory = categoryPersistenceAdapter.createRootCategory("second")
        categoryPersistenceAdapter.createRootCategory("third")

        // root category 2의 sub category 4 생성
        categoryPersistenceAdapter.addSubCategory(secondRootCategory.id!!, "sub")

        // when
        // root category 만 조회
        val allRootCategories = categoryRepository.findAllRootCategories()

        // then
        assertThat(allRootCategories.size).isEqualTo(3)
    }

    @Test
    @Transactional
    fun `루트카테고리에 연결된 서브카테고리를 전체 조회 테스트`() {
        // given
        // root category 1, 2, 3, 4 생성
        val firstRootCategory = categoryPersistenceAdapter.createRootCategory("first")
        val secondRootCategory = categoryPersistenceAdapter.createRootCategory("second")
        categoryPersistenceAdapter.createRootCategory("third")
        categoryPersistenceAdapter.createRootCategory("fourth")

        // root category 1의 sub category 5, 6, 7 생성
        val parentId = firstRootCategory.id!!
        categoryPersistenceAdapter.addSubCategory(parentId, "sub first")
        categoryPersistenceAdapter.addSubCategory(parentId, "sub second")
        categoryPersistenceAdapter.addSubCategory(parentId, "sub third")
        // root category 2의 sub category 8 생성
        categoryPersistenceAdapter.addSubCategory(secondRootCategory.id!!, "second sub first")

        // when
        // root category 1의 sub category 조회
        val allSubCategories = categoryRepository.findAllSubCategories(parentId)

        // then
        assertThat(allSubCategories.size).isEqualTo(3)
    }

    @Test
    @Transactional
    fun `루트카테고리에 연결된 서브 카테고리를 depth를 지정해 조회 테스트`() {
        // given
        // root category 1, 2 생성
        val firstRootCategory = categoryPersistenceAdapter.createRootCategory("first")
        val secondRootCategory = categoryPersistenceAdapter.createRootCategory("second")

        // root category 1의 sub category 3, 4 생성
        // 1 - 3, 4
        val parentId = firstRootCategory.id!!
        val subFirstCategory = categoryPersistenceAdapter.addSubCategory(parentId, "sub first")
        categoryPersistenceAdapter.addSubCategory(parentId, "sub second")
        // root category 2의 sub category 5 생성
        // 2 - 5
        categoryPersistenceAdapter.addSubCategory(secondRootCategory.id!!, "second sub first")
        // root category 1의 sub category 3의 sub category 6 생성
        // 1 - 3, 4 - 6
        categoryPersistenceAdapter.addSubCategory(subFirstCategory.id!!, "depth2Category")

        // when
        // root category 1의 depth 1 category 조회
        // 1 - 3, 4 - 6 / 2 - 5
        val allSubCategoriesByDepth = categoryRepository.findAllSubCategoriesByDepth(parentId, 1)

        // then
        assertThat(allSubCategoriesByDepth.size).isEqualTo(2)
    }

    @Test
    @Transactional
    fun `전체 카테고리를 조회 테스트`() {
        // given
        // root category 1, 2 생성
        val firstRootCategory = categoryPersistenceAdapter.createRootCategory("first")
        val secondRootCategory = categoryPersistenceAdapter.createRootCategory("second")

        // root category 1의 sub category 3, 4 생성
        // 1 - 3, 4
        val parentId = firstRootCategory.id!!
        val subFirstCategory = categoryPersistenceAdapter.addSubCategory(parentId, "sub first")
        categoryPersistenceAdapter.addSubCategory(parentId, "sub second")
        // root category 2의 sub category 5 생성
        // 2 - 5
        categoryPersistenceAdapter.addSubCategory(secondRootCategory.id!!, "second sub first")
        // root category 1의 sub category 3의 sub category 6 생성
        // 1 - 3, 4 - 6
        categoryPersistenceAdapter.addSubCategory(subFirstCategory.id!!, "depth2Category")

        // when
        // root category 1의 depth 1 category 조회
        // 1 - 3, 4 - 6 / 2 - 5 => 총 6개
        val allCategories = categoryRepository.findAll()

        // then
        assertThat(allCategories.size).isEqualTo(6)
    }
}
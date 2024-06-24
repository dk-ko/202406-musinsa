package com.musinsa.shop.category.adapter.`in`.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.musinsa.shop.category.application.port.`in`.CategoryUseCase
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@ExtendWith(SpringExtension::class)
@WebMvcTest(CategoryController::class)
class CategoryControllerTest {
    @MockBean
    private lateinit var categoryUseCase: CategoryUseCase

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Test
    fun createCategoryTest() {
        val categoryName = "category"
        val categoryCreateReqDto = CategoryCreateReqDto(categoryName)
        val categoryResDto = CategoryResDto(1L, categoryName)

        given(categoryUseCase.createRootCategory(categoryName)).willReturn(categoryResDto)

        createCategory(categoryCreateReqDto, ROOT_URL)
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value(categoryName))
    }

    @Test
    fun addSubCategoryTest() {
        val categoryName = "category"
        val categoryCreateReqDto = CategoryCreateReqDto(categoryName)
        val rootCategoryId = 1L
        val categoryResDto = CategoryResDto(rootCategoryId, categoryName)

        given(categoryUseCase.createRootCategory(categoryName)).willReturn(categoryResDto)

        createCategory(categoryCreateReqDto, ROOT_URL)
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))


        val subCategoryName = "Sub Category"
        val categoryAddReqDto = CategoryAddReqDto(rootCategoryId, subCategoryName)
        val subCategoryResDto = CategoryResDto(rootCategoryId, subCategoryName)

        given(categoryUseCase.addSubCategory(rootCategoryId, subCategoryName)).willReturn(subCategoryResDto)

        createCategory(categoryAddReqDto, SUB_URL)
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value(subCategoryName))
    }

    @Test
    fun getAllRootCategoriesTest() {
        val categoryFirstName = "category First"
        val categorySecondName = "category Second"
        val categoryCreateFirstReqDto = CategoryCreateReqDto(categoryFirstName)
        val categoryCreateSecondReqDto = CategoryCreateReqDto(categorySecondName)
        val categoryFirstId = 1L
        val categorySecondId = 2L
        val categoryFirstResDto = CategoryResDto(categoryFirstId, categoryFirstName)
        val categorySecondResDto = CategoryResDto(categorySecondId, categorySecondName)

        given(categoryUseCase.createRootCategory(categoryFirstName)).willReturn(categoryFirstResDto)
        given(categoryUseCase.createRootCategory(categorySecondName)).willReturn(categorySecondResDto)
        given(categoryUseCase.getAllRootCategories()).willReturn(listOf(categoryFirstResDto, categorySecondResDto))

        createCategory(categoryCreateFirstReqDto, ROOT_URL)
        createCategory(categoryCreateSecondReqDto, ROOT_URL)

        mockMvc.perform(
            MockMvcRequestBuilders.get(ROOT_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].id").value(categoryFirstId))
            .andExpect(jsonPath("$[0].name").value(categoryFirstName))
            .andExpect(jsonPath("$[1].id").value(categorySecondId))
            .andExpect(jsonPath("$[1].name").value(categorySecondName))
    }

    @Test
    fun getAllSubCategoriesTest() {
        val categoryName = "category"
        val categoryCreateReqDto = CategoryCreateReqDto(categoryName)
        val rootCategoryId = 1L
        val categoryResDto = CategoryResDto(rootCategoryId, categoryName)

        given(categoryUseCase.createRootCategory(categoryName)).willReturn(categoryResDto)

        createCategory(categoryCreateReqDto, ROOT_URL)

        val subCategoryFirstName = "Sub Category 1"
        val subCategorySecondName = "Sub Category 2"
        val categoryAddReqFirstDto = CategoryAddReqDto(rootCategoryId, subCategoryFirstName)
        val categoryAddReqSecondDto = CategoryAddReqDto(rootCategoryId, subCategorySecondName)
        val subCategoryResFirstDto = CategoryResDto(2L, subCategoryFirstName)
        val subCategoryResSecondDto = CategoryResDto(3L, subCategorySecondName)

        given(categoryUseCase.addSubCategory(rootCategoryId, subCategoryFirstName)).willReturn(subCategoryResFirstDto)
        given(categoryUseCase.addSubCategory(rootCategoryId, subCategorySecondName)).willReturn(subCategoryResSecondDto)
        given(categoryUseCase.getAllSubCategories(rootCategoryId)).willReturn(listOf(subCategoryResFirstDto, subCategoryResSecondDto))

        createCategory(categoryAddReqFirstDto, SUB_URL)
        createCategory(categoryAddReqSecondDto, SUB_URL)

        mockMvc.perform(
            MockMvcRequestBuilders.get("$SUB_URL?parentId={parentId}", rootCategoryId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].id").value(2L))
            .andExpect(jsonPath("$[0].name").value(subCategoryFirstName))
            .andExpect(jsonPath("$[1].id").value(3L))
            .andExpect(jsonPath("$[1].name").value(subCategorySecondName))
    }

    @Test
    fun getAllSubCategoriesByDepthTest() {
        // root category (1) 생성
        val categoryName = "category"
        val categoryCreateReqDto = CategoryCreateReqDto(categoryName)
        val rootCategoryId = 1L
        val categoryResDto = CategoryResDto(rootCategoryId, categoryName)

        given(categoryUseCase.createRootCategory(categoryName)).willReturn(categoryResDto)

        createCategory(categoryCreateReqDto, ROOT_URL)

        // root category (1)의 sub category (2, 3) 생성
        // sub category (3)의 sub category (4) 생성
        val subCategoryFirstName = "Sub Category 1"
        val subCategorySecondName = "Sub Category 2"
        val subSubCategoryFirstName = "Sub Sub Category 1"

        val categoryAddReqFirstDto = CategoryAddReqDto(rootCategoryId, subCategoryFirstName)
        val categoryAddReqSecondDto = CategoryAddReqDto(rootCategoryId, subCategorySecondName)

        val subCategoryResFirstDto = CategoryResDto(2L, subCategoryFirstName)
        val subCategoryResSecondDto = CategoryResDto(3L, subCategorySecondName)

        val subSubCategoryResFirstDto = CategoryResDto(4L, subSubCategoryFirstName)
        val categoryAddReqSecondSubDto = CategoryAddReqDto(subCategoryResSecondDto.id!!, subSubCategoryFirstName)

        given(categoryUseCase.addSubCategory(rootCategoryId, subCategoryFirstName)).willReturn(subCategoryResFirstDto)
        given(categoryUseCase.addSubCategory(rootCategoryId, subCategorySecondName)).willReturn(subCategoryResSecondDto)
        given(categoryUseCase.getAllSubCategoriesByDepth(3L, 1)).willReturn(listOf(subSubCategoryResFirstDto))

        createCategory(categoryAddReqFirstDto, SUB_URL)
        createCategory(categoryAddReqSecondDto, SUB_URL)
        createCategory(categoryAddReqSecondSubDto, SUB_URL)

        // // sub category (3)의 sub category (4) 확인
        mockMvc.perform(
            MockMvcRequestBuilders.get("$SUB_DEPTH_URL?parentId={parentId}&depth={depth}", subCategoryResSecondDto.id, 1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].id").value(4L))
            .andExpect(jsonPath("$[0].name").value(subSubCategoryFirstName))
    }

    private fun createCategory(reqDto: Any, url: String) =
        mockMvc.perform(
            MockMvcRequestBuilders.post(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(reqDto))
        )

    companion object {
        const val ROOT_URL = "/categories/root"
        const val SUB_URL = "/categories/sub"
        const val SUB_DEPTH_URL = "/categories/sub-depth"
    }
}
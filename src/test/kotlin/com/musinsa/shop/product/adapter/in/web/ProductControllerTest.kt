package com.musinsa.shop.product.adapter.`in`.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.musinsa.shop.product.application.port.`in`.ProductUseCase
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
@WebMvcTest(ProductController::class)
class ProductControllerTest {
    @MockBean
    private lateinit var productUseCase: ProductUseCase

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Test
    fun createProductTest() {
        val productId = 1L
        val productName = "name"
        val price = 1_000
        val brandCode = "code"
        val productReqDto = ProductCreateReqDto(productName, price, brandCode, listOf(1))
        val productResDto = ProductResDto(productId, productName, price, brandCode)
        given(productUseCase.createProduct(productName, price, brandCode, listOf(1)))
            .willReturn(productResDto)

        createProduct(productReqDto)
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value(productName))
            .andExpect(jsonPath("$.price").value(price))
            .andExpect(jsonPath("$.brandCode").value(brandCode))
    }

    @Test
    fun updateProductInfoTest() {
        val productId = 1L
        val productName = "name"
        val price = 1_000
        val brandCode = "code"
        val productReqDto = ProductCreateReqDto(productName, price, brandCode, listOf(1))
        val productResDto = ProductResDto(productId, productName, price, brandCode)
        given(productUseCase.createProduct(productName, price, brandCode, listOf(1)))
            .willReturn(productResDto)

        createProduct(productReqDto)

        val updateName = "update"
        val updatePrice = 2_000
        val productUpdateReqDto = ProductUpdateReqDto(productId, updateName, updatePrice)
        given(productUseCase.updateProductInfo(productId, updateName, updatePrice)).willReturn(1)

        mockMvc.perform(
            MockMvcRequestBuilders.patch("/products?productId={productId}", productId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(productUpdateReqDto))
        )

        .andExpect(MockMvcResultMatchers.status().isOk)
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.content().string("1"))
    }

    @Test
    fun updateCategoryOfProductTest() {
        val productId = 1L
        val productName = "name"
        val price = 1_000
        val brandCode = "code"
        val productReqDto = ProductCreateReqDto(productName, price, brandCode, listOf(1))
        val productResDto = ProductResDto(productId, productName, price, brandCode)
        given(productUseCase.createProduct(productName, price, brandCode, listOf(1)))
            .willReturn(productResDto)
        createProduct(productReqDto)

        val categoryOfProductReqDto = CategoryOfProductReqDto(productId, 1L, 2L)
        given(productUseCase.updateCategoryOfProduct(productId, 1L, 2L)).willReturn(1)

        mockMvc.perform(
            MockMvcRequestBuilders.patch("/products/category?productId={productId}", productId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(categoryOfProductReqDto))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().string("1"))
    }

    @Test
    fun deleteProductTest() {
        val productId = 1L
        val productName = "name"
        val price = 1_000
        val brandCode = "code"
        val productReqDto = ProductCreateReqDto(productName, price, brandCode, listOf(1))
        val productResDto = ProductResDto(productId, productName, price, brandCode)
        given(productUseCase.createProduct(productName, price, brandCode, listOf(1)))
            .willReturn(productResDto)
        createProduct(productReqDto)

        mockMvc.perform(
            MockMvcRequestBuilders.delete("/products?productId={productId}", productId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
    }

    private fun createProduct(productReqDto: ProductCreateReqDto) =
        mockMvc.perform(
            MockMvcRequestBuilders.post("/products")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(productReqDto))
        )
}
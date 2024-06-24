package com.musinsa.shop.brand.adapter.`in`.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.musinsa.shop.brand.application.port.`in`.BrandUseCase
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
@WebMvcTest(BrandController::class)
class BrandControllerTest {
    @MockBean
    private lateinit var brandUseCase: BrandUseCase

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Test
    fun createBrandTest() {
        val name = "name"
        val code = "code"
        val brandCreateReqDto = BrandCreateReqDto(name, code)
        val brandResDto = BrandResDto(1L, name, code)

        given(brandUseCase.createBrand(name, code)).willReturn(brandResDto)

        createBrand(brandCreateReqDto)
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))

            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("name"))
            .andExpect(jsonPath("$.code").value("code"))
    }

    @Test
    fun updateBrandTest() {
        // create
        val name = "name"
        val code = "code"
        val id = 1L
        val brandResDto = BrandResDto(id, name, code)
        given(brandUseCase.createBrand(name, code)).willReturn(brandResDto)

        val brandCreateReqDto = BrandCreateReqDto(name, code)
        createBrand(brandCreateReqDto)

        // update
        val updateValue = "update"
        given(brandUseCase.updateBrand(id, updateValue, updateValue)).willReturn(1)
        val brandUpdateReqDto = BrandUpdateReqDto(id, updateValue, updateValue)

        mockMvc.perform(
            MockMvcRequestBuilders.patch("/brands/{id}", brandUpdateReqDto.id)
                .content(mapper.writeValueAsString(brandUpdateReqDto))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("1"))

    }

    @Test
    fun deleteBrandTest() {
        // create
        val name = "name"
        val code = "code"
        val id = 1L
        val brandResDto = BrandResDto(id, name, code)
        given(brandUseCase.createBrand(name, code)).willReturn(brandResDto)

        val brandCreateReqDto = BrandCreateReqDto(name, code)
        createBrand(brandCreateReqDto)

        // delete
        mockMvc.perform(
            MockMvcRequestBuilders.delete("/brands/{id}", id)
                .content(mapper.writeValueAsString(brandCreateReqDto))
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent)
    }

    private fun createBrand(brandCreateReqDto: BrandCreateReqDto) =
        mockMvc.perform(
            MockMvcRequestBuilders.post("/brands")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(brandCreateReqDto))
        )
}
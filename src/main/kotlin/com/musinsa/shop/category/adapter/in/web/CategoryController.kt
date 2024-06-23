package com.musinsa.shop.category.adapter.`in`.web

import com.musinsa.shop.category.application.port.`in`.CategoryUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/categories")
class CategoryController(
    private val categoryUseCase: CategoryUseCase,
) {
    @PostMapping
    fun createRootCategory(
        @RequestBody categoryCreateReqDto: CategoryCreateReqDto)
    : ResponseEntity<CategoryResDto> {
        return ResponseEntity(categoryUseCase.createRootCategory(categoryCreateReqDto.name), HttpStatus.CREATED)
    }

    @PostMapping("/sub")
    fun addSubCategory(
        @RequestBody categoryAddReqDto: CategoryAddReqDto
    ): ResponseEntity<CategoryResDto> {
        return ResponseEntity(categoryUseCase.addSubCategory(
            parentCategoryId = categoryAddReqDto.parentCategoryId,
            subCategoryName = categoryAddReqDto.name,
        ), HttpStatus.CREATED)
    }
    // todo select들 API로 빼기
}
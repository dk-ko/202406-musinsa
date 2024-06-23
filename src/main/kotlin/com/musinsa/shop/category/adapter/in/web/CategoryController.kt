package com.musinsa.shop.category.adapter.`in`.web

import com.musinsa.shop.category.application.port.`in`.CategoryUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/categories")
class CategoryController(
    private val categoryUseCase: CategoryUseCase,
) {
    @PostMapping("/root")
    fun createRootCategory(
        @RequestBody categoryCreateReqDto: CategoryCreateReqDto
    ): ResponseEntity<CategoryResDto> {
        return ResponseEntity(categoryUseCase.createRootCategory(categoryCreateReqDto.name), HttpStatus.CREATED)
    }

    @PostMapping("/sub")
    fun addSubCategory(
        @RequestBody categoryAddReqDto: CategoryAddReqDto
    ): ResponseEntity<CategoryResDto> {
        return ResponseEntity(
            categoryUseCase.addSubCategory(
                parentCategoryId = categoryAddReqDto.parentCategoryId,
                subCategoryName = categoryAddReqDto.name
            ),
            HttpStatus.CREATED
        )
    }

    @GetMapping("/root")
    fun getAllRootCategories(): ResponseEntity<List<CategoryResDto>> {
        return ResponseEntity(categoryUseCase.getAllRootCategories(), HttpStatus.OK)
    }

    @GetMapping("/sub")
    fun getAllSubCategories(
        @RequestParam parentId: Long
    ): ResponseEntity<List<CategoryResDto>> {
        return ResponseEntity(categoryUseCase.getAllSubCategories(parentId), HttpStatus.OK)
    }

    @GetMapping("/sub-depth") // TODO depth 삭제
    fun getAllSubCategoriesByDepth(
        @RequestParam parentId: Long,
        @RequestParam depth: Int,
    ): ResponseEntity<List<CategoryResDto>> {
        return ResponseEntity(categoryUseCase.getAllSubCategoriesByDepth(parentId, depth), HttpStatus.OK)
    }
}
package com.musinsa.shop.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleResourceNotFoundException(
        ex: NotFoundException, request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errorDetails = ErrorResponse(
            message = ex.message ?: "Resource not found",
            details = request.getDescription(false)
        )
        return ResponseEntity(errorDetails, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(DuplicateException::class)
    fun handleResourceDuplicateException(
        ex: DuplicateException, request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errorDetails = ErrorResponse(
            message = ex.message ?: "Resource with already exists",
            details = request.getDescription(false)
        )
        return ResponseEntity(errorDetails, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(
        ex: Exception, request: WebRequest
    ): ResponseEntity<ErrorResponse> {
        val errorDetails = ErrorResponse(
            message = ex.message ?: "Internal Server Error",
            details = request.getDescription(false)
        )
        return ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
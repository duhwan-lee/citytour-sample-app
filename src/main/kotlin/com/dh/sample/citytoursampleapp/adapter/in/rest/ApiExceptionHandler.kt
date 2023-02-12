package com.dh.sample.citytoursampleapp.adapter.`in`.rest

import com.dh.sample.citytoursampleapp.domain.exception.CityTourException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ApiExceptionHandler {

    @ExceptionHandler(CityTourException::class)
    fun apiExceptionHandler(e: CityTourException): ResponseEntity<ErrorResponseBody> {
        val response =
            ErrorResponseBody(e.errorType.name, e.message ?: e.errorType.defaultMsg)
        return ResponseEntity<ErrorResponseBody>(response, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    data class ErrorResponseBody(val code: String, val message: String)
}
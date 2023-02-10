package com.dh.sample.citytoursampleapp.adapter.`in`.data.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

enum class ResponseType(status: HttpStatus) {
    OK(HttpStatus.OK);

    val httpStatus = status

    fun toResponse(message: String, data: Any): ResponseEntity<CommonResponse> {
        return ResponseEntity(CommonResponse(message, data), httpStatus)
    }

    fun toResponse(message: String): ResponseEntity<CommonResponse> {
        return ResponseEntity(CommonResponse(message, null), httpStatus)
    }

    fun toResponse(): ResponseEntity<CommonResponse> {
        return ResponseEntity(CommonResponse("Success", null), httpStatus)
    }
}
package com.dh.sample.citytoursampleapp.adapter.`in`.rest

import com.dh.sample.citytoursampleapp.adapter.`in`.data.request.TourRegisterRequest
import com.dh.sample.citytoursampleapp.adapter.`in`.data.request.TourUpdateRequest
import com.dh.sample.citytoursampleapp.adapter.`in`.data.response.CommonResponse
import com.dh.sample.citytoursampleapp.adapter.`in`.data.response.ResponseType
import com.dh.sample.citytoursampleapp.application.port.`in`.TourUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/tour")
class TourController(private val tourUseCase: TourUseCase) {

    @PostMapping
    fun registerTour(@Valid @RequestBody tourRegisterRequest: TourRegisterRequest): ResponseEntity<CommonResponse> {
        tourRegisterRequest.validate()
        val tour = tourUseCase.insertTourInfo(tourRegisterRequest)
        return ResponseType.OK.toResponse(tour)
    }

    @PutMapping("/{userId}/{tourId}")
    fun updateTour(
        @PathVariable("userId") userId: Long,
        @PathVariable("tourId") tourId: Long,
        @Valid @RequestBody tourUpdateRequest: TourUpdateRequest
    ): ResponseEntity<CommonResponse> {
        tourUpdateRequest.validate()
        val tour = tourUseCase.updateTourInfo(tourId, userId, tourUpdateRequest)
        return ResponseType.OK.toResponse(tour)
    }

    @DeleteMapping("/{userId}/{tourId}")
    fun deleteTour(
        @PathVariable("userId") userId: Long,
        @PathVariable("tourId") tourId: Long
    ): ResponseEntity<CommonResponse> {
        tourUseCase.deleteTourInfo(tourId, userId)
        return ResponseType.OK.toResponse()
    }

    @GetMapping("/{userId}/{tourId}")
    fun getTourInfo(
        @PathVariable("userId") userId: Long,
        @PathVariable("tourId") tourId: Long
    ): ResponseEntity<CommonResponse> {
        val tour = tourUseCase.getTourInfo(tourId)
        return ResponseType.OK.toResponse(tour)
    }
}
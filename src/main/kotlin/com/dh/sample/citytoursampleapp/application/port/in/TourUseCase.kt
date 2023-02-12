package com.dh.sample.citytoursampleapp.application.port.`in`

import com.dh.sample.citytoursampleapp.adapter.`in`.data.request.TourUpdateRequest
import com.dh.sample.citytoursampleapp.adapter.`in`.data.request.TourRegisterRequest
import com.dh.sample.citytoursampleapp.domain.Tour

interface TourUseCase {
    fun insertTourInfo(tourRegisterRequest: TourRegisterRequest): Tour
    fun updateTourInfo(tourId: Long, userId: Long, tourUpdateRequest: TourUpdateRequest): Tour
    fun deleteTourInfo(tourId: Long, userId: Long)
    fun getTourInfo(tourId: Long) : Tour
}
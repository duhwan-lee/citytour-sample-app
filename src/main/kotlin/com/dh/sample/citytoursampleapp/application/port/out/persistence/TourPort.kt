package com.dh.sample.citytoursampleapp.application.port.out.persistence

import com.dh.sample.citytoursampleapp.adapter.out.persistence.entity.EntityTour

interface TourPort {
    fun insertTourSave(entityTour: EntityTour): EntityTour

    fun getTourInfosByUserId(userId: Long): List<EntityTour>
    fun getTourInfo(tourId: Long): EntityTour?

    fun updateTourSave(entityTour: EntityTour): EntityTour
    fun deleteTourInfo(entityTour: EntityTour)
}
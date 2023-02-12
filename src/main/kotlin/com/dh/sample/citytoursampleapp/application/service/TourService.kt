package com.dh.sample.citytoursampleapp.application.service

import com.dh.sample.citytoursampleapp.adapter.`in`.data.request.TourRegisterRequest
import com.dh.sample.citytoursampleapp.adapter.`in`.data.request.TourUpdateRequest
import com.dh.sample.citytoursampleapp.adapter.out.persistence.entity.EntityTour
import com.dh.sample.citytoursampleapp.application.port.`in`.TourUseCase
import com.dh.sample.citytoursampleapp.application.port.out.persistence.CityPort
import com.dh.sample.citytoursampleapp.application.port.out.persistence.TourPort
import com.dh.sample.citytoursampleapp.application.port.out.persistence.UserPort
import com.dh.sample.citytoursampleapp.domain.Tour
import com.dh.sample.citytoursampleapp.domain.exception.CityTourException
import com.dh.sample.citytoursampleapp.domain.exception.ErrorType
import org.springframework.stereotype.Service

@Service
class TourService(
    private val cityPort: CityPort, private val tourPort: TourPort, private val userPort: UserPort
) : TourUseCase {
    override fun insertTourInfo(tourRegisterRequest: TourRegisterRequest): Tour {

        //존재하는 도시정보 인지 확인
        val cityEntity = cityPort.findByCityId(tourRegisterRequest.cityId) ?: run {
            throw CityTourException(ErrorType.NOT_EXIST_CITY)
        }

        val tourEntity = tourPort.insertTourSave(
            EntityTour(
                tourRegisterRequest.userId,
                tourRegisterRequest.cityId,
                tourRegisterRequest.startDate,
                tourRegisterRequest.endDate,
            )
        )
        return Tour.from(tourEntity, cityEntity)
    }

    override fun updateTourInfo(
        tourId: Long, userId: Long, tourUpdateRequest: TourUpdateRequest
    ): Tour {
        userValidation(userId)

        return tourPort.getTourInfo(tourId)?.let {
            //3. city validation
            val cityEntity = cityPort.findByCityId(tourUpdateRequest.cityId) ?: run {
                throw CityTourException(ErrorType.NOT_EXIST_CITY)
            }
            it.update(
                tourUpdateRequest.cityId, tourUpdateRequest.startDate, tourUpdateRequest.endDate
            )
            val savedTourEntity = tourPort.updateTourSave(it)

            Tour.from(savedTourEntity, cityEntity)
        } ?: kotlin.run {
            //2. tour validation
            throw CityTourException(ErrorType.NOT_EXIST_TOUR)
        }
    }

    //TODO : 인터셉터로 변경해서 처리할 수 있다.
    private fun userValidation(userId: Long) {
        if (!userPort.isExistUser(userId)) { //1. user validation
            throw CityTourException(ErrorType.NOT_EXIST_USER)
        }
    }

    override fun deleteTourInfo(tourId: Long, userId: Long) {
        userValidation(userId)
        tourPort.deleteTourInfo(tourId)
    }

    override fun getTourInfo(tourId: Long): Tour {
        return tourPort.getTourInfo(tourId)?.let {
            val cityEntity = cityPort.findByCityId(it.cityId)!!
            Tour.from(it, cityEntity)
        } ?: kotlin.run {
            //2. tour validation
            throw CityTourException(ErrorType.NOT_EXIST_TOUR)
        }
    }
}
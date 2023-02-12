package com.dh.sample.citytoursampleapp.application.service

import com.dh.sample.citytoursampleapp.adapter.`in`.data.request.CityRegisterRequest
import com.dh.sample.citytoursampleapp.adapter.`in`.data.request.CityUpdateRequest
import com.dh.sample.citytoursampleapp.adapter.out.persistence.entity.EntityCity
import com.dh.sample.citytoursampleapp.application.port.`in`.CityUseCase
import com.dh.sample.citytoursampleapp.application.port.out.persistence.CityPort
import com.dh.sample.citytoursampleapp.application.port.out.persistence.TourPort
import com.dh.sample.citytoursampleapp.domain.City
import com.dh.sample.citytoursampleapp.domain.exception.CityTourException
import com.dh.sample.citytoursampleapp.domain.exception.ErrorType
import org.springframework.stereotype.Service

@Service
class CityService(private val cityPort: CityPort, private val tourPort: TourPort) : CityUseCase {
    override fun isExist(cityName: String): Boolean {
        return cityPort.findByCityName(cityName)?.let { true } ?: false
    }

    override fun insertCity(cityRegisterRequest: CityRegisterRequest): City {
        if (isExist(cityRegisterRequest.cityName)) {
            throw CityTourException(ErrorType.DUPLICATED_CITY_NAME)
        }
        val entityCity = cityPort.insertEntitySave(
            EntityCity(
                cityRegisterRequest.cityName,
                cityRegisterRequest.country
            )
        )
        return City.from(entityCity)
    }

    override fun updateCityInfo(cityId: Long, cityUpdateRequest: CityUpdateRequest): City {
        cityPort.findByCityId(cityId)?.let {
            it.modify(cityUpdateRequest.cityName)
            val entity = cityPort.updateEntitySave(it)
            return City.from(entity)
        } ?: kotlin.run {
            throw CityTourException(ErrorType.NOT_EXIST_CITY)
        }
    }

    override fun getCityInfo(cityName: String): City {
        return cityPort.findByCityName(cityName)?.let {
            City.from(it)
        } ?: kotlin.run {
            throw CityTourException(ErrorType.NOT_EXIST_CITY)
        }
    }

    override fun getCityInfo(cityId: Long): City {
        return cityPort.findByCityId(cityId)?.let {
            return City.from(it)
        } ?: kotlin.run {
            throw CityTourException(ErrorType.NOT_EXIST_CITY)
        }
    }

    override fun deleteCityInfo(cityId: Long) {
        if (tourPort.getTourInfosByCityId(cityId).isEmpty()) {
            cityPort.deleteCityInfo(cityId)
        } else {
            throw CityTourException(ErrorType.USED_CITY)
        }
    }


}
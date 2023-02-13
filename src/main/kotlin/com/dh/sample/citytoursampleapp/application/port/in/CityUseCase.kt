package com.dh.sample.citytoursampleapp.application.port.`in`

import com.dh.sample.citytoursampleapp.adapter.`in`.data.request.CityRegisterRequest
import com.dh.sample.citytoursampleapp.adapter.`in`.data.request.CityUpdateRequest
import com.dh.sample.citytoursampleapp.adapter.out.persistence.dto.OnTourCity
import com.dh.sample.citytoursampleapp.adapter.out.persistence.dto.PlannedCity
import com.dh.sample.citytoursampleapp.domain.City

interface CityUseCase {
    fun isExist(cityName: String): Boolean
    fun insertCity(cityRegisterRequest: CityRegisterRequest): City
    fun updateCityInfo(cityId: Long, cityUpdateRequest: CityUpdateRequest): City
    fun getCityInfo(cityName: String): City
    fun getCityInfo(cityId: Long): City
    fun deleteCityInfo(cityId: Long)
    fun getOnTourCityList(userId: Long): List<OnTourCity>
    fun getPlannedCityList(userId: Long): List<PlannedCity>
    fun getDefaultCityListExclude(excludeCityIdList: List<Long>): List<City>
}
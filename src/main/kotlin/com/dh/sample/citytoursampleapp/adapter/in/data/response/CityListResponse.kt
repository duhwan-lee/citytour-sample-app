package com.dh.sample.citytoursampleapp.adapter.`in`.data.response

import com.dh.sample.citytoursampleapp.adapter.out.persistence.dto.OnTourCity
import com.dh.sample.citytoursampleapp.adapter.out.persistence.dto.PlannedCity
import com.dh.sample.citytoursampleapp.domain.City

data class CityListResponse(
    val onTourCityList: List<OnTourCity>,
    val plannedCityList: List<PlannedCity>,
    val defaultCityList: List<City>
)

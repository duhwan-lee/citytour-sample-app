package com.dh.sample.citytoursampleapp.adapter.`in`.data.request

import jakarta.validation.constraints.NotBlank

data class CityUpdateRequest(@field:NotBlank val cityName: String)
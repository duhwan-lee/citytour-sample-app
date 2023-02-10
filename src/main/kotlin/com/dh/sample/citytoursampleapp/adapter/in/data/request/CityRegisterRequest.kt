package com.dh.sample.citytoursampleapp.adapter.`in`.data.request

import jakarta.validation.constraints.NotBlank

data class CityRegisterRequest(@field:NotBlank val cityName: String)
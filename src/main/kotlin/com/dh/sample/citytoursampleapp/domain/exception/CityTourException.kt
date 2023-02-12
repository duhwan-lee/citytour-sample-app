package com.dh.sample.citytoursampleapp.domain.exception

class CityTourException(val errorType: ErrorType) : RuntimeException(errorType.defaultMsg)
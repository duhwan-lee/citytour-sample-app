package com.dh.sample.citytoursampleapp.adapter.`in`.rest

import com.dh.sample.citytoursampleapp.adapter.`in`.data.request.CityRegisterRequest
import com.dh.sample.citytoursampleapp.adapter.`in`.data.request.CityUpdateRequest
import com.dh.sample.citytoursampleapp.adapter.`in`.data.response.CityListResponse
import com.dh.sample.citytoursampleapp.adapter.`in`.data.response.CommonResponse
import com.dh.sample.citytoursampleapp.adapter.`in`.data.response.ResponseType
import com.dh.sample.citytoursampleapp.application.port.`in`.CityUseCase
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/city")
class CityController(private val cityUseCase: CityUseCase) {

    @PostMapping("")
    fun registerCity(@Valid @RequestBody cityRegisterRequest: CityRegisterRequest): ResponseEntity<CommonResponse> {
        val city = cityUseCase.insertCity(cityRegisterRequest)
        return ResponseType.OK.toResponse(city)
    }

    @GetMapping("")
    fun findCity(@RequestParam("cityName") cityName: String): ResponseEntity<CommonResponse> {
        val city = cityUseCase.getCityInfo(cityName)
        return ResponseType.OK.toResponse(city)
    }

    @GetMapping("/{cityId}")
    fun findCity(@PathVariable("cityId") cityId: Long): ResponseEntity<CommonResponse> {
        val city = cityUseCase.getCityInfo(cityId)
        return ResponseType.OK.toResponse(city)
    }

    @PutMapping("/{cityId}")
    fun updateCity(
        @PathVariable("cityId") cityId: Long,
        @Valid @RequestBody cityUpdateRequest: CityUpdateRequest
    ): ResponseEntity<CommonResponse> {
        val city = cityUseCase.updateCityInfo(cityId, cityUpdateRequest)
        return ResponseType.OK.toResponse(city)
    }

    @DeleteMapping("/{cityId}")
    fun deleteCity(
        @PathVariable("cityId") cityId: Long
    ): ResponseEntity<CommonResponse> {
        cityUseCase.deleteCityInfo(cityId)
        return ResponseType.OK.toResponse()
    }

    @GetMapping("/list/{userId}")
    fun findCityList(@PathVariable("userId") userId: Long): ResponseEntity<CommonResponse> {
        val onTourCityList = cityUseCase.getOnTourCityList(userId)
        val plannedCityList = cityUseCase.getPlannedCityList(userId)
        val excludeList = plannedCityList.map { it.getCityId() }
        val defaultCityList = cityUseCase.getDefaultCityListExclude(excludeList)
        return ResponseType.OK.toResponse(
            CityListResponse(
                onTourCityList,
                plannedCityList,
                defaultCityList
            )
        )
    }

}
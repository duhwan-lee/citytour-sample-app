package com.dh.sample.citytoursampleapp.adapter.`in`.rest

import com.dh.sample.citytoursampleapp.adapter.`in`.data.request.CityRegisterRequest
import com.dh.sample.citytoursampleapp.adapter.`in`.data.request.CityUpdateRequest
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

    @GetMapping("")
    fun findCity(@RequestParam("id") id: Long): ResponseEntity<CommonResponse> {
        val city = cityUseCase.getCityInfo(id)
        return ResponseType.OK.toResponse(city)
    }

    @PutMapping("/{id}")
    fun updateCity(
        @PathVariable("id") id: Long,
        @Valid @RequestBody cityUpdateRequest: CityUpdateRequest
    ): ResponseEntity<CommonResponse> {
        val city = cityUseCase.updateCityInfo(id, cityUpdateRequest)
        return ResponseType.OK.toResponse(city)
    }

    @DeleteMapping("/{id}")
    fun deleteCity(
        @PathVariable("id") id: Long
    ): ResponseEntity<CommonResponse> {
        TODO("구현해야 함")
    }

}
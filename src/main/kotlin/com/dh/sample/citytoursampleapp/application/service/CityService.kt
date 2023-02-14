package com.dh.sample.citytoursampleapp.application.service

import com.dh.sample.citytoursampleapp.adapter.`in`.data.request.CityRegisterRequest
import com.dh.sample.citytoursampleapp.adapter.`in`.data.request.CityUpdateRequest
import com.dh.sample.citytoursampleapp.adapter.out.persistence.dto.OnTourCity
import com.dh.sample.citytoursampleapp.adapter.out.persistence.dto.PlannedCity
import com.dh.sample.citytoursampleapp.adapter.out.persistence.entity.EntityCity
import com.dh.sample.citytoursampleapp.application.port.`in`.CityUseCase
import com.dh.sample.citytoursampleapp.application.port.out.persistence.CityPort
import com.dh.sample.citytoursampleapp.application.port.out.persistence.SearchPort
import com.dh.sample.citytoursampleapp.application.port.out.persistence.TourPort
import com.dh.sample.citytoursampleapp.application.port.out.persistence.UserPort
import com.dh.sample.citytoursampleapp.domain.City
import com.dh.sample.citytoursampleapp.domain.exception.CityTourException
import com.dh.sample.citytoursampleapp.domain.exception.ErrorType
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CityService(
    private val cityPort: CityPort,
    private val tourPort: TourPort,
    private val userPort: UserPort,
    private val searchPort: SearchPort
) : CityUseCase {
    companion object {
        fun isWithIn24H(source: LocalDateTime, now: LocalDateTime): Boolean =
            source.isBefore(now) && source.plusDays(1).isAfter(now) && now.minusDays(1)
                .isBefore(source)
    }

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
            searchPort.saveSearchInfo(it.cityId!!)
            City.from(it)
        } ?: kotlin.run {
            throw CityTourException(ErrorType.NOT_EXIST_CITY)
        }
    }

    override fun getCityInfo(cityId: Long): City {
        return cityPort.findByCityId(cityId)?.let {
            searchPort.saveSearchInfo(it.cityId!!)
            City.from(it)
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

    override fun getOnTourCityList(userId: Long): List<OnTourCity> {
        //1. 유저 확인
        if (userPort.isExistUser(userId)) {
            return cityPort.findOnTourCityByUserId(userId) //현재 진행중인 여행목록
        }
        return listOf()
    }

    override fun getDefaultCityListExclude(excludeCityIdList: List<Long>): List<City> {
        if (excludeCityIdList.size >= 10) {
            return listOf()
        }
        //여행중 / 여행예정 도시가 제외된 전체 도시 목록
        return getDefaultCityList().filterNot { excludeCityIdList.contains(it.cityId) }
            .subList(0, 10 - excludeCityIdList.size)
    }

    //TODO : 성능에 따라 이 결과값은 캐싱할 수도 있다.
    fun getDefaultCityList(): List<City> {
        val now = LocalDateTime.now()
        //전체 도시 목록
        val allCity = cityPort.findAllCities()
        //24시간 내에 등록된 도시 목록
        val w24CreateCityList = allCity.filter { isWithIn24H(it.createAt, now) }
        val w24CreateCityIdList = w24CreateCityList.map { it.cityId }

        //24시간 내에 등록된 도시 제외 전체 목록
        val allCityExcludeW24CreateCity =
            allCity.filterNot { w24CreateCityIdList.contains(it.cityId) }
        //7일 이내 검색된 도시 목록
        val searchedCityList = allCityExcludeW24CreateCity.filter { it.isWithIn7daySearch(now) }
        val searchedCityIdList = searchedCityList.map { it.cityId }

        //7일 이내 검색된 도시가 제외된 목록의 셔플
        val shuffledAnotherList =
            allCityExcludeW24CreateCity.filterNot { searchedCityIdList.contains(it.cityId) }
                .shuffled()

        return (w24CreateCityList + searchedCityList + shuffledAnotherList).map { City.from(it) }
            .subList(0, 10)
    }

    override fun getPlannedCityList(userId: Long): List<PlannedCity> =
        cityPort.findDistinctPlannedCityByUserId(userId)
}
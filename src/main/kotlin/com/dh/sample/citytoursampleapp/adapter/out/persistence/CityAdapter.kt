package com.dh.sample.citytoursampleapp.adapter.out.persistence

import com.dh.sample.citytoursampleapp.adapter.out.persistence.dto.OnTourCity
import com.dh.sample.citytoursampleapp.adapter.out.persistence.dto.PlannedCity
import com.dh.sample.citytoursampleapp.adapter.out.persistence.entity.EntityCity
import com.dh.sample.citytoursampleapp.adapter.out.persistence.entity.EntityCitySearch
import com.dh.sample.citytoursampleapp.adapter.out.persistence.repository.CityRepository
import com.dh.sample.citytoursampleapp.adapter.out.persistence.repository.SearchRepository
import com.dh.sample.citytoursampleapp.application.port.out.persistence.CityPort
import com.dh.sample.citytoursampleapp.application.port.out.persistence.SearchPort
import com.dh.sample.citytoursampleapp.domain.exception.CityTourException
import com.dh.sample.citytoursampleapp.domain.exception.ErrorType
import com.dh.sample.citytoursampleapp.infrastructure.Adapter
import org.springframework.data.repository.findByIdOrNull

@Adapter
class CityAdapter(
    private val cityRepository: CityRepository,
    private val searchRepository: SearchRepository
) : CityPort, SearchPort {
    override fun findByCityName(cityName: String): EntityCity? {
        return cityRepository.findByCityNameEquals(cityName)
    }

    override fun insertEntitySave(entityCity: EntityCity): EntityCity {
        //TODO : insert만을 위해 persistable를 상속받아 insert만 작동시킬 수 있다.
        return cityRepository.save(entityCity)
    }

    override fun findByCityId(cityId: Long): EntityCity? {
        return cityRepository.findByIdOrNull(cityId)
    }

    override fun updateEntitySave(entityCity: EntityCity): EntityCity {
        return cityRepository.save(entityCity)
    }

    override fun deleteCityInfo(cityId: Long) {
        cityRepository.findByIdOrNull(cityId)?.let {
            cityRepository.delete(it)
        } ?: kotlin.run {
            throw CityTourException(ErrorType.NOT_EXIST_CITY)
        }
    }

    //현재 진행중인 여행목록 With 정렬(출발일 빠른순)
    override fun findOnTourCityByUserId(userId: Long): List<OnTourCity> {
        return cityRepository.findOnTourCityByUserId(userId).sortedBy { it.getStartDate() }
    }

    override fun findDistinctPlannedCityByUserId(userId: Long): List<PlannedCity> {
        val map = mutableMapOf<Long, PlannedCity>()
        for (city in cityRepository.findPlannedCityByUserId(userId)) {
            val cityInMap = map.getOrDefault(city.getCityId(), city)
            if (cityInMap.getStartDate().isAfter(city.getStartDate())) {
                map[city.getCityId()] = city
            } else {
                map[city.getCityId()] = cityInMap
            }
        }
        return map.values.sortedBy { it.getStartDate() }
    }

    override fun saveSearchInfo(cityId: Long) {
        searchRepository.save(EntityCitySearch(cityId))
    }

    override fun findAllCities(): List<EntityCity> {
        return cityRepository.findAll()
    }
}
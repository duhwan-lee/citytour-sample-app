package com.dh.sample.citytoursampleapp.adapter.out.persistence

import com.dh.sample.citytoursampleapp.adapter.out.persistence.entity.EntityCity
import com.dh.sample.citytoursampleapp.adapter.out.persistence.repository.CityRepository
import com.dh.sample.citytoursampleapp.application.port.out.persistence.CityPort
import com.dh.sample.citytoursampleapp.domain.exception.CityTourException
import com.dh.sample.citytoursampleapp.domain.exception.ErrorType
import com.dh.sample.citytoursampleapp.infrastructure.Adapter
import org.springframework.data.repository.findByIdOrNull

@Adapter
class CityAdapter(private val cityRepository: CityRepository) : CityPort {
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
}
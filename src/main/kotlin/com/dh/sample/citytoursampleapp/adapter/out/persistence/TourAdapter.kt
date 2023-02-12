package com.dh.sample.citytoursampleapp.adapter.out.persistence

import com.dh.sample.citytoursampleapp.adapter.out.persistence.entity.EntityTour
import com.dh.sample.citytoursampleapp.adapter.out.persistence.repository.TourRepository
import com.dh.sample.citytoursampleapp.application.port.out.persistence.TourPort
import com.dh.sample.citytoursampleapp.domain.exception.CityTourException
import com.dh.sample.citytoursampleapp.domain.exception.ErrorType
import com.dh.sample.citytoursampleapp.infrastructure.Adapter
import org.springframework.data.repository.findByIdOrNull

@Adapter
class TourAdapter(private val tourRepository: TourRepository) : TourPort {
    override fun insertTourSave(entityTour: EntityTour): EntityTour {
        return tourRepository.save(entityTour)
    }

    override fun getTourInfosByUserId(userId: Long): List<EntityTour> {
        //user table을 분리해야 하지만 우선 유저 등록 api가 없어서 여행 테이블을 기준으로 유저를 조회한다.
        return tourRepository.findByUserIDEquals(userId)
    }

    override fun getTourInfo(tourId: Long): EntityTour? {
        return tourRepository.findByIdOrNull(tourId)
    }

    override fun updateTourSave(entityTour: EntityTour): EntityTour {
        return tourRepository.save(entityTour)
    }

    override fun deleteTourInfo(tourId: Long) {
        //기취소 건을 확인하기 위해 flag를 사용하는게 좋겠지만 일단 delete로 진행
        tourRepository.findByIdOrNull(tourId)?.let {
            tourRepository.delete(it)
        } ?: kotlin.run {
            //2. tour validation
            throw CityTourException(ErrorType.NOT_EXIST_TOUR)
        }
    }

    override fun getTourInfosByCityId(cityId: Long): List<EntityTour> {
        return tourRepository.findByCityIdEquals(cityId)
    }
}
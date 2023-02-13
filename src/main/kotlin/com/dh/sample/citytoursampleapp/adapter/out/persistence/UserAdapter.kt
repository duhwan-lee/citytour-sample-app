package com.dh.sample.citytoursampleapp.adapter.out.persistence

import com.dh.sample.citytoursampleapp.adapter.out.persistence.repository.TourRepository
import com.dh.sample.citytoursampleapp.application.port.out.persistence.UserPort
import com.dh.sample.citytoursampleapp.infrastructure.Adapter

@Adapter
class UserAdapter(private val tourRepository: TourRepository) : UserPort {
    override fun isExistUser(userId: Long): Boolean {
        //user table을 분리해야 하지만 우선 유저 등록 api가 없어서 여행 테이블을 기준으로 유저를 조회한다.
        return tourRepository.findByUserIdEquals(userId).isNotEmpty()
    }
}
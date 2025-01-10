package com.doseyenc.main.domain

import com.doseyenc.main.data.source.UpcomingRepository
import javax.inject.Inject

class UpcomingUseCase @Inject constructor(
    private val upcomingRepository: UpcomingRepository
) {
    fun getUpcoming() =
        upcomingRepository.getUpcoming()
}
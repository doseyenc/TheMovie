package com.doseyenc.main.data.source

import com.doseyenc.main.data.source.service.UpcomingService
import javax.inject.Inject

class UpcomingDataSource @Inject constructor(
    private val upcomingService: UpcomingService
) {
    fun getUpcoming(token: String) = upcomingService.getUpcoming(
        token = token,
        language = "en-US",
        page = 1
    )
}
package com.doseyenc.main.data.source


import com.doseyenc.themovie.BuildConfig
import javax.inject.Inject

class UpcomingRepository @Inject constructor(private val upcomingDataSource: com.doseyenc.main.data.source.UpcomingDataSource) {
    private val token = "Bearer ${BuildConfig.API_KEY}"
    fun getUpcoming() = upcomingDataSource.getUpcoming(token)
}
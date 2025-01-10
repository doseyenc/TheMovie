package com.doseyenc.main.data.source


import com.doseyenc.themovie.BuildConfig
import javax.inject.Inject

class NowPlayingRepository @Inject constructor(
    private val nowPlayingDataSource: NowPlayingDataSource
) {
    private val token = "Bearer ${BuildConfig.API_KEY}"
    fun getNowPlaying(page: Int) = nowPlayingDataSource.getNowPlaying(token, page)
}
package com.doseyenc.main.data.source

import com.doseyenc.main.data.source.service.NowPlayingService
import javax.inject.Inject

class NowPlayingDataSource @Inject constructor(
    private val nowPlayingService: NowPlayingService
) {
    fun getNowPlaying(token: String, page: Int) = nowPlayingService.getNowPlaying(
        token = token,
        page = page
    )
}
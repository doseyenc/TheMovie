package com.doseyenc.main.domain

import com.doseyenc.main.data.source.NowPlayingRepository
import javax.inject.Inject

class NowPlayingUseCase @Inject constructor(
    private val nowPlayingRepository: NowPlayingRepository
) {
    fun getNowPlayingList(page: Int) =
        nowPlayingRepository.getNowPlaying(page = page)
}
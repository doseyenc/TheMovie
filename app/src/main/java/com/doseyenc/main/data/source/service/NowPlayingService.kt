package com.doseyenc.main.data.source.service

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NowPlayingService {
    @GET("movie/now_playing")
    fun getNowPlaying(
        @Header("Authorization") token: String,
        @Header("accept") accept: String = "application/json",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Observable<_root_ide_package_.com.doseyenc.main.data.model.NowPlayingResponse>
}

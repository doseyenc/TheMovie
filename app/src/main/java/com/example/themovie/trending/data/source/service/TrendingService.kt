package com.example.themovie.trending.data.source.service

import com.example.themovie.trending.data.model.TrendingResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface TrendingService {

    @GET("trending/all/week")
    fun getTrending(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
    ): Single<TrendingResponse>

}
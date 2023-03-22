package com.example.themovie.trending.data.source.remote

import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import com.example.themovie.trending.data.model.TrendingResponse
import com.example.themovie.trending.data.source.TrendingDataSource
import com.example.themovie.trending.data.source.service.TrendingService


class TrendingRemoteDataSource @Inject constructor(
    private val trendingService: TrendingService
): TrendingDataSource.Remote {
    override fun getTrending(
        language: String,
        token: String
    ):Observable<TrendingResponse>{
        return trendingService.getTrending(
            language = language,
            token = token
        ).toObservable()

    }
}
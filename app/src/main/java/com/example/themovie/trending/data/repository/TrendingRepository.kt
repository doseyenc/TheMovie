package com.example.themovie.trending.data.repository

import com.example.themovie.common.extensions.Resource
import com.example.themovie.common.extensions.ResourceReactiveExtensions.remote
import com.example.themovie.trending.data.model.TrendingResponse
import com.example.themovie.trending.data.source.TrendingDataSource
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class TrendingRepository @Inject constructor(
    private val trendingDataSource: TrendingDataSource.Remote
) {

    fun getTrending(
        language: String,
        token: String
    ): Observable<Resource<TrendingResponse>> {
        return trendingDataSource
            .getTrending(
                language = language,
                token = token,
            )
            .remote()
    }
}
package com.example.themovie.detail.data.repository

import com.example.themovie.common.extensions.Resource
import com.example.themovie.common.extensions.ResourceReactiveExtensions.remote
import com.example.themovie.detail.data.model.TvSeriesDetailResponse
import com.example.themovie.detail.data.source.TvSeriesDetailDataSource
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class TvSeriesDetailRepository @Inject constructor(
    private val tvSeriesDetailDataSource: TvSeriesDetailDataSource.Remote
) {
    fun getTvSeriesDetail(
        language: String,
        token: String,
        id: Int,
    ): Observable<Resource<TvSeriesDetailResponse>> {
        return tvSeriesDetailDataSource
            .getTvSeriesDetail(
                language = language,
                token = token,
                id = id,
            )
            .remote()
    }
}
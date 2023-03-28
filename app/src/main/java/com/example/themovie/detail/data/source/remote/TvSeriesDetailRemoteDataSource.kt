package com.example.themovie.detail.data.source.remote

import com.example.themovie.detail.data.model.TvSeriesDetailResponse
import com.example.themovie.detail.data.source.TvSeriesDetailDataSource
import com.example.themovie.detail.data.source.service.TvSeriesDetailService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class TvSeriesDetailRemoteDataSource @Inject constructor(
    private val tvSeriesDetailService: TvSeriesDetailService
) : TvSeriesDetailDataSource.Remote {
    override fun getTvSeriesDetail(
        language: String,
        token: String,
        id: Int,
    ): Observable<TvSeriesDetailResponse> {
        return tvSeriesDetailService.getTvSeriesDetail(
            language = language,
            token = token,
            id = id
        ).toObservable()
    }
}
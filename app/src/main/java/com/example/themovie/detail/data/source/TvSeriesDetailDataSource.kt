package com.example.themovie.detail.data.source


import com.example.themovie.detail.data.model.TvSeriesDetailResponse
import io.reactivex.rxjava3.core.Observable

interface TvSeriesDetailDataSource {
    interface Remote {
        fun getTvSeriesDetail(
            language: String,
            token: String,
            id: Int,
        ): Observable<TvSeriesDetailResponse>
    }
}
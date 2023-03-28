package com.example.themovie.detail.data.source.remote

import com.example.themovie.detail.data.model.MovieDetailResponse
import com.example.themovie.detail.data.source.MovieDetailDataSource
import com.example.themovie.detail.data.source.service.MovieDetailService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MovieDetailRemoteDataSource @Inject constructor(
    private val movieDetailService: MovieDetailService
) : MovieDetailDataSource.Remote {
    override fun getDetail(
        language: String,
        token: String,
        id: Int,
    ): Observable<MovieDetailResponse> {
        return movieDetailService.getDetail(
            language = language,
            token = token,
            id = id
        ).toObservable()
    }
}
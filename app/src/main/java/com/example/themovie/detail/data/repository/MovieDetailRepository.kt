package com.example.themovie.detail.data.repository

import com.example.themovie.common.extensions.Resource
import com.example.themovie.common.extensions.ResourceReactiveExtensions.remote
import com.example.themovie.detail.data.model.MovieDetailResponse
import com.example.themovie.detail.data.source.MovieDetailDataSource
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(
    private val movieDetailDataSource: MovieDetailDataSource.Remote
) {

    fun getDetail(
        language: String,
        token: String,
        id: Int,
    ): Observable<Resource<MovieDetailResponse>> {
        return movieDetailDataSource
            .getDetail(
                language = language,
                token = token,
                id = id,
            )
            .remote()
    }
}
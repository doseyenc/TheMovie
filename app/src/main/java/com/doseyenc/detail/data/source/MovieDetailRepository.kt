package com.doseyenc.detail.data.source


import com.doseyenc.themovie.BuildConfig
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(
    private val movieDetailDataSource: MovieDetailDataSource
) {
    private val token = "Bearer ${BuildConfig.API_KEY}"
    fun getMovieDetail(id: Int) =
        movieDetailDataSource.getMovieDetail(
            token,
            id = id
        )
}
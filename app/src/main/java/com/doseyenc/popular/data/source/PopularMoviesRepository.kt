package com.doseyenc.popular.data.source

import com.doseyenc.themovie.BuildConfig
import javax.inject.Inject

class PopularMoviesRepository @Inject constructor(private val popularMoviesDataSource: PopularMoviesDataSource) {
    private val token = "Bearer ${BuildConfig.API_KEY}"
    fun getPopularMovies(page: Int) =
        popularMoviesDataSource.getPopularMovies(
            token = token,
            page = page
        )
}
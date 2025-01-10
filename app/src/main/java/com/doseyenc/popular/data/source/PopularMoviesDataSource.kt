package com.doseyenc.popular.data.source

import com.doseyenc.popular.data.source.service.PopularMoviesService
import javax.inject.Inject

class PopularMoviesDataSource @Inject constructor(
    private val popularMoviesService: PopularMoviesService
) {
    fun getPopularMovies(token: String, page: Int) = popularMoviesService.getPopularMovies(
        token = token,
        language = "en-US",
        page = page
    )
}
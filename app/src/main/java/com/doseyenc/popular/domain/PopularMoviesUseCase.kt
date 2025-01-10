package com.doseyenc.popular.domain

import com.doseyenc.popular.data.source.PopularMoviesRepository
import javax.inject.Inject


class PopularMoviesUseCase @Inject constructor(
    private val popularMoviesRepository: PopularMoviesRepository
) {
    fun getPopularMovies(page: Int) =
        popularMoviesRepository.getPopularMovies(
            page = page
        )
}
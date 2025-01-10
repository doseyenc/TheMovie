package com.doseyenc.detail.domain

import com.doseyenc.detail.data.source.MovieDetailRepository
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository
) {
    fun getMovieDetail(id: Int) =
        movieDetailRepository.getMovieDetail(id = id)
}
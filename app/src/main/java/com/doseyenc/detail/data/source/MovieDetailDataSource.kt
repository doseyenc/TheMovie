package com.doseyenc.detail.data.source

import com.doseyenc.detail.data.source.service.MovieDetailService
import javax.inject.Inject

class MovieDetailDataSource @Inject constructor(
    private val movieDetailService: MovieDetailService
) {
    fun getMovieDetail(token: String, id: Int) = movieDetailService.getDetail(
        token = token,
        id = id
    )
}
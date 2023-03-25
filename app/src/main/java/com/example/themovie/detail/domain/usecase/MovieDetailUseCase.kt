package com.example.themovie.detail.domain.usecase

import com.example.themovie.common.extensions.Resource
import com.example.themovie.common.extensions.mapOnData
import com.example.themovie.detail.data.repository.MovieDetailRepository
import com.example.themovie.detail.domain.mapper.MovieDetailMapper
import com.example.themovie.detail.domain.model.MovieDetailData
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class MovieDetailUseCase @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository,
    private val movieDetailMapper: MovieDetailMapper
) {
    fun getDetail(
        language: String,
        token: String,
        id: Int
    ): Observable<Resource<MovieDetailData>> {
        return movieDetailRepository
            .getDetail(
                token = token,
                language = language,
                id = id
            ).mapOnData {
                movieDetailMapper.mapResultsFromResponse(it)
            }
    }
}
package com.example.themovie.detail.domain.usecase

import com.example.themovie.common.extensions.Resource
import com.example.themovie.common.extensions.mapOnData
import com.example.themovie.detail.data.repository.TvSeriesDetailRepository
import com.example.themovie.detail.domain.mapper.TvSeriesDetailMapper
import com.example.themovie.detail.domain.model.TvSeriesDetailData
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class TvSeriesDetailUseCase @Inject constructor(
    private val tvSeriesDetailRepository: TvSeriesDetailRepository,
    private val tvSeriesDetailMapper: TvSeriesDetailMapper
) {
    fun getDetail(
        language: String,
        token: String,
        id: Int
    ): Observable<Resource<TvSeriesDetailData>> {
        return tvSeriesDetailRepository
            .getTvSeriesDetail(
                token = token,
                language = language,
                id = id
            ).mapOnData {
                tvSeriesDetailMapper.mapResultsFromResponse(it)
            }
    }
}
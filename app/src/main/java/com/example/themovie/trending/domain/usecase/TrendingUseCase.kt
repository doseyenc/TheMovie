package com.example.themovie.trending.domain.usecase

import com.example.themovie.common.extensions.Resource
import com.example.themovie.common.extensions.mapOnData
import com.example.themovie.trending.data.repository.TrendingRepository
import com.example.themovie.trending.domain.mapper.TrendingMapper
import com.example.themovie.trending.domain.model.TrendingData
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class TrendingUseCase @Inject constructor(
    private val trendingRepository: TrendingRepository,
    private val trendingMapper: TrendingMapper
) {

    fun getTrending(
        language: String,
        token: String,
    ): Observable<Resource<TrendingData>> {
        return trendingRepository
            .getTrending(
                token = token,
                language = language,
            ).mapOnData {
                trendingMapper.mapResultsFromResponse(it)
            }
    }
}
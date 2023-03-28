package com.example.themovie.trending.domain.mapper

import com.example.themovie.trending.data.model.TrendingResponse
import com.example.themovie.trending.data.model.TrendingResponseData
import com.example.themovie.trending.domain.model.TrendingData
import com.example.themovie.trending.domain.model.TrendingDataResultsData
import javax.inject.Inject

class TrendingMapper @Inject constructor() {
    fun mapResultsFromResponse(
        response: TrendingResponse
    ): TrendingData {
        return TrendingData(
            page = response.page,
            results = MapResultsFromResponse(response.results)
        )
    }

    private fun MapResultsFromResponse(
        results: List<TrendingResponseData>?
    ): List<TrendingDataResultsData>? {
        return results?.map {
            TrendingDataResultsData(
                id = it.id,
                mediaType = it.mediaType,
                title = it.title,
                overview = it.overview,
                posterPath = it.poster_path,
                backdrop_path = it.backdrop_path,
                releaseDate = it.release_date,
                voteAverage = it.vote_average,
                voteCount = it.vote_count,
                popularity = it.popularity,
                originalLanguage = it.original_language,
                originalTitle = it.original_title,
            )
        }
    }
}



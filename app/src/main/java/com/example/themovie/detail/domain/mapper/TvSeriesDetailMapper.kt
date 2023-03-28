package com.example.themovie.detail.domain.mapper


import com.example.themovie.detail.data.model.Genre
import com.example.themovie.detail.data.model.TvSeriesDetailResponse
import com.example.themovie.detail.domain.model.GenreData
import com.example.themovie.detail.domain.model.TvSeriesDetailData
import javax.inject.Inject

class TvSeriesDetailMapper @Inject constructor() {

    fun mapResultsFromResponse(
        response: TvSeriesDetailResponse?
    ): TvSeriesDetailData {
        return TvSeriesDetailData(
            id = response?.id,
            name = response?.name,
            overview = response?.overview,
            posterPath = response?.posterPath,
            backdropPath = response?.backdropPath,
            firstAirDate = response?.firstAirDate,
            lastAirDate = response?.lastAirDate,
            voteAverage = response?.voteAverage,
            voteCount = response?.voteCount,
            popularity = response?.popularity,
            originalLanguage = response?.originalLanguage,
            originalName = response?.original_name,
            numberOfEpisodes = response?.numberOfEpisodes,
            numberOfSeasons = response?.numberOfSeasons,
            status = response?.status,
            type = response?.type,
            genres = mapGenresFromResponse(response?.genres)
        )
    }

    private fun mapGenresFromResponse(genres: List<Genre>?): List<GenreData>? {
        return genres?.map {
            GenreData(
                id = it.id,
                name = it.name
            )
        }
    }
}
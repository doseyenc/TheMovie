package com.example.themovie.detail.domain.mapper

import com.example.themovie.detail.data.model.MovieDetailResponse
import com.example.themovie.detail.domain.model.MovieDetailData
import javax.inject.Inject

class MovieDetailMapper @Inject constructor() {

    fun mapResultsFromResponse(
        response: MovieDetailResponse?
    ): MovieDetailData {
        return MovieDetailData(
            id = response?.id,
            title = response?.title,
            overview = response?.overview,
            posterPath = response?.posterPath,
            backdropPath = response?.backdropPath,
            releaseDate = response?.releaseDate,
            voteAverage = response?.voteAverage,
            voteCount = response?.voteCount,
            popularity = response?.popularity,
            originalLanguage = response?.originalLanguage,
            originalTitle = response?.originalTitle,
            budget = response?.budget,
            revenue = response?.revenue,
        )
    }
}
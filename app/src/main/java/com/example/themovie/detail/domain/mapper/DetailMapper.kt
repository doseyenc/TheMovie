package com.example.themovie.detail.domain.mapper

import com.example.themovie.detail.data.model.DetailResponse
import com.example.themovie.detail.domain.model.DetailData

class DetailMapper {

    fun mapResultsFromResponse(
        response: DetailResponse?
    ): DetailData {
        return DetailData(
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
        )
    }
}
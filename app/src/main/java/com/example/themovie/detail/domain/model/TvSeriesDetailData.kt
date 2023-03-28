package com.example.themovie.detail.domain.model

data class TvSeriesDetailData(
    val id: Int?,
    val name: String?,
    val overview: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val firstAirDate: String?,
    val lastAirDate: String?,
    val voteAverage: Double?,
    val voteCount: Int?,
    val popularity: Double?,
    val originalLanguage: String?,
    val originalName: String?,
    val numberOfEpisodes: Int?,
    val numberOfSeasons: Int?,
    val status: String?,
    val type: String?,
    val genres: List<GenreData>?,

)
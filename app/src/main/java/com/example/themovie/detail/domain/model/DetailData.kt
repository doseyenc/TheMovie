package com.example.themovie.detail.domain.model

data class DetailData(
    val id: Int?,
    val title: String?,
    val overview: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String?,
    val voteAverage: Double?,
    val voteCount: Int?,
    val popularity: Double?,
    val originalLanguage: String?,
    val originalTitle: String?,
)

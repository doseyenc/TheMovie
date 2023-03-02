package com.example.themovie.trending.domain.model

data class TrendingData(
    val page: Int? = null,
    val results: List<TrendingDataResultsData>? = null,
)
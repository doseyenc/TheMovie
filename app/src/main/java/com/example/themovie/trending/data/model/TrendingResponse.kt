package com.example.themovie.trending.data.model

import com.google.gson.annotations.SerializedName

data class TrendingResponse (
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<TrendingResponseData>? = null,
    @SerializedName("total_pages")
    val total_pages: Int? = null,
    @SerializedName("total_results")
    val total_results: Int? = null
)
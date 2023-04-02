package com.example.themovie.search.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse (
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<SearchResponseData>,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int
        )
package com.example.themovie.search.data.source.service

import com.example.themovie.search.data.model.SearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header

interface SearchService {

    @GET("search/multi")
    fun getSearchResults(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
        @Header("query") query: String,
        @Header("page") page: Int,
        @Header("include_adult") includeAdult: Boolean,
    ): Single<SearchResponse>

}
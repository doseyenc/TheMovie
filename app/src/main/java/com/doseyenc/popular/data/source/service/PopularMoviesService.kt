package com.doseyenc.popular.data.source.service

import com.doseyenc.popular.data.model.PopularMoviesResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface PopularMoviesService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Header("Authorization") token: String,
        @Header("accept") accept: String = "application/json",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Observable<PopularMoviesResponse>
}

package com.doseyenc.detail.data.source.service

import com.doseyenc.detail.data.model.MovieDetailResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {

    @GET("movie/{id}")
    fun getDetail(
        @Path("id") id: Int,
        @Header("Authorization") token: String,
        @Query("language") language: String = "en-US"
    ): Observable<MovieDetailResponse>
}
package com.doseyenc.main.data.source.service

import com.doseyenc.main.data.model.UpcomingResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UpcomingService {
    @GET("movie/upcoming")
    fun getUpcoming(
        @Header("Authorization") token: String,
        @Header("accept") accept: String = "application/json",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Observable<UpcomingResponse>
}

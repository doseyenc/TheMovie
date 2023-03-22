package com.example.themovie.detail.data.source.service

import com.example.themovie.detail.data.model.DetailResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface DetailService {

    @GET("movie/{id}")
    fun getDetail(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
        @Path("id")  id : Int
    ): Single<DetailResponse>

}
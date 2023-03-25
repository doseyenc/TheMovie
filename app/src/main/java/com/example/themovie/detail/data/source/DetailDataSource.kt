package com.example.themovie.detail.data.source

import com.example.themovie.detail.data.model.MovieDetailResponse
import io.reactivex.rxjava3.core.Observable

interface DetailDataSource {
    interface Remote {
        fun getDetail(
            language: String,
            token: String,
            id: Int,
        ): Observable<MovieDetailResponse>
    }
}
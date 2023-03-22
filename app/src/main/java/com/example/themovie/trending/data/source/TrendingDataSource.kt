package com.example.themovie.trending.data.source

import com.example.themovie.trending.data.model.TrendingResponse
import io.reactivex.rxjava3.core.Observable

interface TrendingDataSource {
    interface Remote {
        fun getTrending(
            language: String,
            token: String,
        ): Observable<TrendingResponse>
    }
}
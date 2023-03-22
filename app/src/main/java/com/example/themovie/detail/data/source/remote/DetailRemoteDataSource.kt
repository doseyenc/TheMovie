package com.example.themovie.detail.data.source.remote

import com.example.themovie.detail.data.model.DetailResponse
import com.example.themovie.detail.data.source.DetailDataSource
import com.example.themovie.detail.data.source.service.DetailService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DetailRemoteDataSource @Inject constructor(
    private val detailService: DetailService
) : DetailDataSource.Remote {
    override fun getDetail(
        language: String,
        token: String,
        id: Int,
    ): Observable<DetailResponse> {
        return detailService.getDetail(
            language = language,
            token = token,
            id = id
        ).toObservable()
    }
}
package com.example.themovie.detail.data.repository

import com.example.themovie.common.extensions.Resource
import com.example.themovie.common.extensions.ResourceReactiveExtensions.remote
import com.example.themovie.detail.data.model.DetailResponse
import com.example.themovie.detail.data.source.DetailDataSource
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val detailDataSource: DetailDataSource.Remote
) {

    fun getDetail(
        language: String,
        token: String,
        id: Int,
    ): Observable<Resource<DetailResponse>> {
        return detailDataSource
            .getDetail(
                language = language,
                token = token,
                id = id,
            )
            .remote()
    }
}
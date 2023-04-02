package com.example.themovie.search.data.repository

import com.example.themovie.common.extensions.Resource
import com.example.themovie.common.extensions.ResourceReactiveExtensions.remote
import com.example.themovie.search.data.model.SearchResponse
import com.example.themovie.search.data.source.SearchDataSource
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val searchDataSource: SearchDataSource.Remote
) {
    fun getSearchResults(
        token: String,
        language: String,
        query: String,
        page: Int,
        includeAdult: Boolean,
    ): Observable<Resource<SearchResponse>> {
        return searchDataSource
            .getSearchResults(
                token = token,
                language = language,
                query = query,
                page = page,
                includeAdult = includeAdult,
            )
            .remote()
    }
}
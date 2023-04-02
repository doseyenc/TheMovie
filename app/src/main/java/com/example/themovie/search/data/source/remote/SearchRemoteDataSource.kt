package com.example.themovie.search.data.source.remote

import com.example.themovie.search.data.model.SearchResponse
import com.example.themovie.search.data.source.SearchDataSource
import com.example.themovie.search.data.source.service.SearchService
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(
    private val searchService: SearchService
) : SearchDataSource.Remote {
    override fun getSearchResults(
        token: String,
        language: String,
        query: String,
        page: Int,
        includeAdult: Boolean
    ): Observable<SearchResponse> {
        return searchService.getSearchResults(
            token = token,
            language = language,
            query = query,
            page = page,
            includeAdult = includeAdult
        ).toObservable()

    }
}
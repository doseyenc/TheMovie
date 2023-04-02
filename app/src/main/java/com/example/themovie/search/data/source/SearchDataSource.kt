package com.example.themovie.search.data.source

import com.example.themovie.search.data.model.SearchResponse
import io.reactivex.rxjava3.core.Observable

interface SearchDataSource {
    interface Remote {
        fun getSearchResults(
            token: String,
            language: String,
            query: String,
            page: Int,
            includeAdult: Boolean
        ): Observable<SearchResponse>
    }
}
package com.example.themovie.detail.domain.usecase

import com.example.themovie.common.extensions.Resource
import com.example.themovie.common.extensions.mapOnData
import com.example.themovie.detail.data.repository.DetailRepository
import com.example.themovie.detail.domain.mapper.DetailMapper
import com.example.themovie.detail.domain.model.DetailData
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class DetailUseCase @Inject constructor(
    private val detailRepository: DetailRepository,
    private val detailMapper: DetailMapper
) {
    fun getDetail(
        language: String,
        token: String,
        id: Int
    ): Observable<Resource<DetailData>> {
        return detailRepository
            .getDetail(
                token = token,
                language = language,
                id = id
            ).mapOnData {
                detailMapper.mapResultsFromResponse(it)
            }
    }
}
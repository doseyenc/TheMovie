package com.example.themovie.detail.ui.viewstate

import com.example.themovie.detail.domain.model.DetailData

sealed class DetailStatusViewState {
    object Loading : DetailStatusViewState()
    object Empty : DetailStatusViewState()
    data class Success(val detailData: DetailData?) : DetailStatusViewState()
    data class Error(val throwable: Throwable) : DetailStatusViewState()
}


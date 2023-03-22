package com.example.themovie.trending.ui.viewstate

import com.example.themovie.trending.domain.model.TrendingData

sealed class TrendingStatusViewState {
    object Loading : TrendingStatusViewState()
    object Empty : TrendingStatusViewState()
    data class Success(val trendingData: TrendingData?) : TrendingStatusViewState()
    data class Error(val throwable: Throwable) : TrendingStatusViewState()
}


package com.example.themovie.detail.ui.viewstate


import com.example.themovie.detail.domain.model.TvSeriesDetailData

sealed class TvSeriesDetailStatusViewState {
    object Loading : TvSeriesDetailStatusViewState()
    object Empty : TvSeriesDetailStatusViewState()
    data class Success(val tvSeriesDetailData: TvSeriesDetailData?) : TvSeriesDetailStatusViewState()
    data class Error(val throwable: Throwable) : TvSeriesDetailStatusViewState()
}


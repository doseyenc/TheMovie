package com.example.themovie.detail.ui.viewstate

import com.example.themovie.detail.domain.model.MovieDetailData

sealed class MovieDetailStatusViewState {
    object Loading : MovieDetailStatusViewState()
    object Empty : MovieDetailStatusViewState()
    data class Success(val movieDetailData: MovieDetailData?) : MovieDetailStatusViewState()
    data class Error(val throwable: Throwable) : MovieDetailStatusViewState()
}


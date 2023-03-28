package com.example.themovie.detail.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.themovie.common.extensions.ResourceReactiveExtensions.subscribe
import com.example.themovie.detail.domain.model.MovieDetailData
import com.example.themovie.detail.domain.usecase.MovieDetailUseCase
import com.example.themovie.detail.ui.viewstate.MovieDetailStatusViewState
import com.tourist.common.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailUseCase: MovieDetailUseCase
) : BaseViewModel() {

    private val stateLiveData: MutableLiveData<MovieDetailStatusViewState> = MutableLiveData()

    fun getStateLiveData(): LiveData<MovieDetailStatusViewState> = stateLiveData


    fun getDetail(
        language: String,
        token: String,
        id : Int
    ) {
        movieDetailUseCase
            .getDetail(
                token = token,
                language = language,
                id = id
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                onSuccess = {
                    onGetDetailResponseReady(it)
                },
                onLoading = {
                    onGetDetailLoading()
                },
                onError = {
                    onGetDetailResponseFail(it)
                }
            )
    }

    private fun onGetDetailResponseReady(movieDetailData: MovieDetailData) {
        stateLiveData.value = MovieDetailStatusViewState.Success(movieDetailData)
    }

    private fun onGetDetailLoading() {
        stateLiveData.value = MovieDetailStatusViewState.Loading
    }

    private fun onGetDetailResponseFail(throwable: Throwable) {
        stateLiveData.value = MovieDetailStatusViewState.Error(throwable)
    }


}
package com.example.themovie.trending.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.themovie.trending.domain.model.TrendingData
import com.example.themovie.trending.domain.usecase.TrendingUseCase
import com.example.themovie.trending.ui.viewstate.TrendingStatusViewState
import com.example.themovie.common.extensions.ResourceReactiveExtensions.subscribe
import com.tourist.common.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject


@HiltViewModel
class TrendingViewModel @Inject constructor(
    private val trendingUseCase: TrendingUseCase
) : BaseViewModel() {

    private val stateLiveData: MutableLiveData<TrendingStatusViewState> = MutableLiveData()

    fun getStateLiveData(): LiveData<TrendingStatusViewState> = stateLiveData


    fun getTrending(
        language: String,
        token: String,
    ) {
        trendingUseCase
            .getTrending(
                token = token,
                language = language
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                onSuccess = {
                    onGetTrendingResponseReady(it)
                },
                onLoading = {
                    onGetTrendingLoading()
                },
                onError = {
                    onGetTrendingResponseFail(it)
                }
            )
    }

    private fun onGetTrendingResponseReady(trendingData: TrendingData) {
        stateLiveData.value = TrendingStatusViewState.Success(trendingData)
    }

    private fun onGetTrendingLoading() {
        stateLiveData.value = TrendingStatusViewState.Loading
    }

    private fun onGetTrendingResponseFail(throwable: Throwable) {
        stateLiveData.value = TrendingStatusViewState.Error(throwable)
    }


}
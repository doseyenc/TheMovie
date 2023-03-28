package com.example.themovie.detail.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.themovie.common.extensions.ResourceReactiveExtensions.subscribe
import com.example.themovie.detail.domain.model.TvSeriesDetailData
import com.example.themovie.detail.domain.usecase.TvSeriesDetailUseCase
import com.example.themovie.detail.ui.viewstate.TvSeriesDetailStatusViewState
import com.tourist.common.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class TvSeriesDetailViewModel @Inject constructor(
    private val tvSeriesDetailUseCase: TvSeriesDetailUseCase
) : BaseViewModel() {

    private val stateLiveData: MutableLiveData<TvSeriesDetailStatusViewState> = MutableLiveData()

    fun getStateLiveData(): LiveData<TvSeriesDetailStatusViewState> = stateLiveData


    fun getDetail(
        language: String,
        token: String,
        id: Int
    ) {
        tvSeriesDetailUseCase
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

    private fun onGetDetailResponseReady(tvSeriesDetailData: TvSeriesDetailData) {
        stateLiveData.value = TvSeriesDetailStatusViewState.Success(tvSeriesDetailData)
    }

    private fun onGetDetailLoading() {
        stateLiveData.value = TvSeriesDetailStatusViewState.Loading
    }

    private fun onGetDetailResponseFail(throwable: Throwable) {
        stateLiveData.value = TvSeriesDetailStatusViewState.Error(throwable)
    }


}
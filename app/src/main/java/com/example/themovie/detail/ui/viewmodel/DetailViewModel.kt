package com.example.themovie.detail.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.themovie.common.extensions.ResourceReactiveExtensions.subscribe
import com.example.themovie.detail.domain.model.DetailData
import com.example.themovie.detail.domain.usecase.DetailUseCase
import com.example.themovie.detail.ui.viewstate.DetailStatusViewState
import com.tourist.common.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCase: DetailUseCase
) : BaseViewModel() {

    private val stateLiveData: MutableLiveData<DetailStatusViewState> = MutableLiveData()

    fun getStateLiveData(): LiveData<DetailStatusViewState> = stateLiveData


    fun getDetail(
        language: String,
        token: String,
        id : Int
    ) {
        detailUseCase
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

    private fun onGetDetailResponseReady(detailData: DetailData) {
        stateLiveData.value = DetailStatusViewState.Success(detailData)
    }

    private fun onGetDetailLoading() {
        stateLiveData.value = DetailStatusViewState.Loading
    }

    private fun onGetDetailResponseFail(throwable: Throwable) {
        stateLiveData.value = DetailStatusViewState.Error(throwable)
    }


}
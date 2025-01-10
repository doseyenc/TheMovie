package com.doseyenc.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.doseyenc.main.data.model.Result
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val showProgress = SingleLiveEvent<Boolean>()
    val errorMessage = SingleLiveEvent<String>()

    private var clickedNavigateToDetail = SingleLiveEvent<Int>()
    val navigateToDetail: LiveData<Int> get() = clickedNavigateToDetail

    fun navigateToDetail(movie: Result) {
        clickedNavigateToDetail.value = movie.id ?: 0
    }

    protected fun handleError(error: Throwable) {
        val message = error.message ?: "An unknown error occurred"
        errorMessage.postValue(message)
    }

    protected fun Disposable.addToDisposable() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
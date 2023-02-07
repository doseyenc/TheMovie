package com.tourist.common.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable


open class BaseViewModel : ViewModel(), CompositeDisposableOwner {

    override val disposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposeSubscriptions()
    }
}
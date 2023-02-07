package com.tourist.common.viewmodel

import io.reactivex.rxjava3.disposables.CompositeDisposable

interface CompositeDisposableOwner {

    val disposable: CompositeDisposable

    fun disposeSubscriptions() {
        disposable.dispose()
    }
}
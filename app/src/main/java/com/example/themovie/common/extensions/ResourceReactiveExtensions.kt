package com.example.themovie.common.extensions


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

object ResourceReactiveExtensions {

    fun <T : Any> Observable<T>.remote(startWithLoading: Boolean = true):
            Observable<Resource<T>> {
        return map<Resource<T>> { Resource.Success(it) }
            .onErrorReturn { Resource.Error(it) }
            .subscribeOn(Schedulers.io())
            .run { if (startWithLoading) { startWithItem(Resource.Loading()) } else this }
    }

    fun <T : Any> Single<T>.remote(startWithLoading: Boolean = true): Observable<Resource<T>> =
        toObservable().remote(startWithLoading)

    fun Completable.remote(startWithLoading: Boolean = true): Observable<Resource<Unit>> {
        return andThen(Observable.just(Unit)).remote(startWithLoading)
    }

    fun <T> Observable<Resource<T>>.subscribe(
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit = {},
        onLoading: () -> Unit = {},
        onStatusChanged: (Status) -> Unit = {},
    ): Disposable =
        observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                onStatusChanged(Status.Content)
                onSuccess(it)
            }
            .doOnLoading {
                onStatusChanged(Status.Loading)
                onLoading()
            }
            .doOnResourceError { error ->
                onStatusChanged(Status.Error(error))
                onError(error)
            }
            .doOnError { error ->
                onStatusChanged(Status.Error(error))
                onError(error)
            }
            .subscribe({}, {})
}

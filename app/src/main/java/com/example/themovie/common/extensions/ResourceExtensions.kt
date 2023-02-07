package com.example.themovie.common.extensions

import io.reactivex.rxjava3.core.Observable

fun <T, R> Observable<Resource<T>>.mapOnData(block: (T) -> R): Observable<Resource<R>> =
    map { resource ->
        resource.mapData(block)
    }

fun <T> Observable<Resource<T>>.doOnSuccess(onSuccess: (T) -> (Unit)): Observable<Resource<T>> {
    return doOnNextPredicate(
        { it is Resource.Success },
        { onSuccess((it as Resource.Success).data) }
    )
}

fun <T> Observable<Resource<T>>.doOnLoading(onLoading: () -> (Unit)): Observable<Resource<T>> {
    return doOnNextPredicate({ it is Resource.Loading }, { onLoading() })
}

fun <T> Observable<Resource<T>>.doOnResourceError(onError: (Throwable) -> (Unit)): Observable<Resource<T>> {
    return doOnNextPredicate(
        { it is Resource.Error },
        { onError((it as Resource.Error).exception) }
    )
}

private fun <T> Observable<T>.doOnNextPredicate(
    predicate: (T) -> (Boolean),
    block: (T) -> Unit
): Observable<T> {
    return this.doOnNext { if (predicate.invoke(it)) block(it) }
}

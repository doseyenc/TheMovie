package com.example.themovie.common.extensions

sealed class Resource<T> {

    data class Success<T>(val data: T) : Resource<T>()

    data class Error<T>(val exception: Throwable) : Resource<T>()

    data class Loading<T>(val data: T? = null) : Resource<T>()

    fun <R> mapData(transform: (T) -> R): Resource<R> = when (this) {
        is Success -> Success(
            transform(data)
        )
        is Error -> Error(
            exception
        )
        is Loading -> Loading(
            data?.let { transform(it) }
        )
    }
}
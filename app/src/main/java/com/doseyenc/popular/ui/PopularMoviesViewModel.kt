package com.doseyenc.popular.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doseyenc.common.BaseViewModel
import com.doseyenc.common.SingleLiveEvent
import com.doseyenc.main.data.model.Result
import com.doseyenc.popular.domain.PopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
    private val useCase: PopularMoviesUseCase
) : BaseViewModel() {

    private val _popularMovies = MutableLiveData<List<Result>>()
    val popularMovies: LiveData<List<Result>> get() = _popularMovies

    private val allMovies = mutableListOf<Result>()
    private var currentPage = 1

    private var clickedBack = SingleLiveEvent<Boolean>()
    val navigateToBack: LiveData<Boolean> get() = clickedBack

    init {
        getPopularMovies(page = currentPage)
    }

    private fun getPopularMovies(page: Int) {
        if (showProgress.value == true) return

        showProgress.value = true
        useCase.getPopularMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                if (page == 1) showProgress.value = true
            }
            .doFinally {
                showProgress.value = false
            }
            .subscribe(
                { response ->
                    val newMovies = response.results?.filterNotNull() ?: emptyList()
                    allMovies.addAll(newMovies)
                    _popularMovies.value = ArrayList(allMovies)
                    currentPage++
                },
                { error ->
                    handleError(error)
                    showProgress.value = false
                }
            ).addToDisposable()
    }

    fun loadMoreMovies() {
        getPopularMovies(page = currentPage)
    }

    fun navigateBack() {
        clickedBack.value = true
    }
}


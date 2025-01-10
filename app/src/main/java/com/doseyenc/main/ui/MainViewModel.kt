package com.doseyenc.main.ui

import android.util.Pair
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doseyenc.common.BaseViewModel
import com.doseyenc.main.data.model.Result
import com.doseyenc.main.domain.NowPlayingUseCase
import com.doseyenc.main.domain.UpcomingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val upcomingUseCase: UpcomingUseCase,
    private val nowPlayingUseCase: NowPlayingUseCase
) : BaseViewModel() {

    private val _sliderMovies = MutableLiveData<List<Result>>()
    val sliderMovies: LiveData<List<Result>> get() = _sliderMovies

    private val _movies = MutableLiveData<List<Result>>()
    val movies: LiveData<List<Result>> get() = _movies

    private var currentPage = 1
    private val allMovies = mutableListOf<Result>()

    init {
        getMovies(page = currentPage)
    }


    private fun getMovies(page: Int) {
        val upcomingObservable = upcomingUseCase.getUpcoming()
        val nowPlayingObservable = nowPlayingUseCase.getNowPlayingList(page = 1)

        Observable.zip(
            upcomingObservable,
            nowPlayingObservable
        ) { upcoming, nowPlaying ->
            Pair(upcoming.results, nowPlaying.results)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                if (page == 1) showProgress.value = true
            }
            .doFinally {
                if (page == 1) showProgress.value = false
            }
            .subscribe(
                { resultPair ->
                    _sliderMovies.value =
                        resultPair.first?.filterNotNull()?.subList(0, 5) ?: emptyList()
                    val newMovies = resultPair.second?.filterNotNull() ?: emptyList()
                    allMovies.addAll(newMovies)
                    _movies.value = allMovies.toList()
                    currentPage = page
                },
                { error ->
                    handleError(error)
                }
            ).addToDisposable()
    }


    fun loadMoreMovies() {
        if (showProgress.value == true) return

        showProgress.value = true
        nowPlayingUseCase.getNowPlayingList(page = currentPage + 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                showProgress.value = true
            }
            .doFinally {
                showProgress.value = false
            }
            .subscribe(
                { response ->
                    val newMovies = response.results?.filterNotNull() ?: emptyList()
                    allMovies.addAll(newMovies)
                    _movies.value = allMovies.toList()
                    currentPage++
                },
                { error ->
                    handleError(error)
                    showProgress.value = false
                }
            ).addToDisposable()
    }
}

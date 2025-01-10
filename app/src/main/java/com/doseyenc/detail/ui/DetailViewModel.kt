package com.doseyenc.detail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.doseyenc.common.BaseViewModel
import com.doseyenc.common.SingleLiveEvent
import com.doseyenc.detail.data.model.MovieDetailResponse
import com.doseyenc.detail.domain.MovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCase: MovieDetailUseCase
) : BaseViewModel() {

    private val _movieDetail = MutableLiveData<MovieDetailResponse>()
    val movieDetail: LiveData<MovieDetailResponse> get() = _movieDetail

    private var clickedBack = SingleLiveEvent<Boolean>()
    val navigateToBack: LiveData<Boolean> get() = clickedBack

    private var clickedPopularMovies = SingleLiveEvent<Boolean>()
    val navigatePopularMovies: LiveData<Boolean> get() = clickedPopularMovies

    fun getMovieDetail(id: Int) {
        if (showProgress.value == true) return

        showProgress.value = true
        detailUseCase.getMovieDetail(id = id)
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
                    _movieDetail.value = response
                },
                { error ->
                    handleError(error)
                    showProgress.value = false
                }
            ).addToDisposable()
    }

    fun getAverageRating(rate: Double?): String {
        return if (rate != null) {
            if (rate % 1.0 == 0.0) {
                rate.toInt().toString()
            } else {
                String.format(Locale.US, "%.1f", rate)
            }
        } else {
            "N/A"
        }
    }

    fun formatReleaseDate(date: String?): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)

            date?.let { data ->
                val parsedDate = inputFormat.parse(data)
                parsedDate?.let { outputFormat.format(it) } ?: "N/A"
            } ?: "N/A"
        } catch (e: Exception) {
            "N/A"
        }
    }

    fun onBackButtonClicked() {
        clickedBack.value = true
    }

    fun navigatePopularMovies() {
        clickedPopularMovies.value = true
    }
}

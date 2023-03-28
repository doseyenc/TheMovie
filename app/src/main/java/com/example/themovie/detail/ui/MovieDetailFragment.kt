package com.example.themovie.detail.ui


import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.themovie.common.util.Constants.API_KEY

import com.example.themovie.common.util.Constants.BASE_URL_IMAGE
import com.example.themovie.common.util.createPlaceHolder
import com.example.themovie.common.util.getDeviceLanguage
import com.example.themovie.common.util.setImage
import com.example.themovie.common.view.BaseFragment
import com.example.themovie.databinding.FragmentMovieDetailBinding
import com.example.themovie.detail.domain.model.MovieDetailData
import com.example.themovie.detail.ui.viewmodel.MovieDetailViewModel
import com.example.themovie.detail.ui.viewstate.MovieDetailStatusViewState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun init() {
        setupView()
        setupViewModel()
        hideStatusBar()
        hideNavigationBar()
    }

    private fun setupViewModel() {
        val apiKey = API_KEY
        with(movieDetailViewModel) {
            getDetail(
                language = getDeviceLanguage(),
                token = "Bearer $apiKey",
                id = args.movieId
            )
            getStateLiveData().observe(viewLifecycleOwner) {
                renderDetailStatusViewState(it)
            }
        }

    }

    private fun renderDetailStatusViewState(viewState: MovieDetailStatusViewState) =
        when (viewState) {
            is MovieDetailStatusViewState.Loading -> loadingInProgress()
            is MovieDetailStatusViewState.Empty -> emptyState()
            is MovieDetailStatusViewState.Success -> displayData(viewState.movieDetailData)
            is MovieDetailStatusViewState.Error -> errorHandle(viewState.throwable)
        }

    private fun errorHandle(throwable: Throwable) {
        binding.detailStateLayout.loading()
        Log.e("MovieDetailErrorHandle", throwable.toString())
    }

    private fun displayData(movieDetailData: MovieDetailData?) {
        Log.e("MovieDetail", movieDetailData.toString())
        with(binding) {
            detailStateLayout.content()
            textViewMovieTitle.text = movieDetailData?.originalTitle
            shapeableImageViewMoviePoster.setImage(
                BASE_URL_IMAGE + "/" + movieDetailData?.posterPath,
                createPlaceHolder(requireContext())
            )
            expandTv.text = movieDetailData?.overview
            textViewBudgetValue.text = movieDetailData?.budget.toString()
            textViewRevenueValue.text = movieDetailData?.revenue.toString()
            textViewReleaseDateValue.text = movieDetailData?.releaseDate
            textViewVoteAverageValue.text = movieDetailData?.voteAverage.toString()
            textViewVoteCountValue.text = movieDetailData?.voteCount.toString()
        }

    }

    private fun emptyState() {
        binding.detailStateLayout.loading()
        Log.e("MovieDetail", "emptyState")
    }

    private fun loadingInProgress() {
        binding.detailStateLayout.loading()
        Log.e("MovieDetail", "loadingInProgress")
    }

    private fun setupView() {
        with(binding) {
            imageViewBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

}
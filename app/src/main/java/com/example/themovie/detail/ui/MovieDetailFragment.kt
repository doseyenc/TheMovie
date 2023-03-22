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
import com.example.themovie.detail.domain.model.DetailData
import com.example.themovie.detail.ui.viewmodel.DetailViewModel
import com.example.themovie.detail.ui.viewstate.DetailStatusViewState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {
    private val detailViewModel: DetailViewModel by viewModels()
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun init() {
        setupView()
        setupViewModel()
        hideStatusBar()
        hideNavigationBar()
    }

    private fun setupViewModel() {
        val apiKey = API_KEY
        with(detailViewModel) {
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

    private fun renderDetailStatusViewState(viewState: DetailStatusViewState) =
        when (viewState) {
            is DetailStatusViewState.Loading -> loadingInProgress()
            is DetailStatusViewState.Empty -> emptyState()
            is DetailStatusViewState.Success -> displayData(viewState.detailData)
            is DetailStatusViewState.Error -> errorHandle(viewState.throwable)
        }

    private fun errorHandle(throwable: Throwable) {
        binding.detailStateLayout.loading()
        Log.e("MovieDetailErrorHandle", throwable.toString())
    }

    private fun displayData(detailData: DetailData?) {
        Log.e("MovieDetail", detailData.toString())
        with(binding) {
            detailStateLayout.content()
            textViewMovieTitle.text = detailData?.originalTitle
            shapeableImageViewMoviePoster.setImage(
                BASE_URL_IMAGE + "/" + detailData?.posterPath,
                createPlaceHolder(requireContext())
            )
            expandTv.text = detailData?.overview
            textViewBudgetValue.text = detailData?.budget.toString()
            textViewRevenueValue.text = detailData?.revenue.toString()
            textViewReleaseDateValue.text = detailData?.releaseDate
            textViewVoteAverageValue.text = detailData?.voteAverage.toString()
            textViewVoteCountValue.text = detailData?.voteCount.toString()
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
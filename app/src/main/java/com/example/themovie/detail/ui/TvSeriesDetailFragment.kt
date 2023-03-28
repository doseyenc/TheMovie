package com.example.themovie.detail.ui

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.themovie.common.util.Constants
import com.example.themovie.common.util.Constants.API_KEY
import com.example.themovie.common.util.createPlaceHolder
import com.example.themovie.common.util.getDeviceLanguage
import com.example.themovie.common.util.setImage
import com.example.themovie.common.view.BaseFragment
import com.example.themovie.databinding.FragmentTvSeriesDetailBinding
import com.example.themovie.detail.domain.model.TvSeriesDetailData
import com.example.themovie.detail.ui.viewmodel.TvSeriesDetailViewModel
import com.example.themovie.detail.ui.viewstate.TvSeriesDetailStatusViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvSeriesDetailFragment : BaseFragment<FragmentTvSeriesDetailBinding>() {
    private val tvSeriesDetailViewModel: TvSeriesDetailViewModel by viewModels()
    private val args: TvSeriesDetailFragmentArgs by navArgs()
    override fun init() {
        setupView()
        setupViewModel()
    }

    private fun setupViewModel() {
        val apiKey = API_KEY
        with(tvSeriesDetailViewModel) {
            getDetail(
                language = getDeviceLanguage(),
                token = "Bearer $apiKey",
                id = args.tvSeriesId
            )
            getStateLiveData().observe(viewLifecycleOwner) {
                renderTvSeriesDetailStatusViewState(it)
            }
        }
    }

    private fun renderTvSeriesDetailStatusViewState(viewState: TvSeriesDetailStatusViewState) =
        when (viewState) {
            is TvSeriesDetailStatusViewState.Loading -> loadingInProgress()
            is TvSeriesDetailStatusViewState.Empty -> emptyState()
            is TvSeriesDetailStatusViewState.Success -> displayData(viewState.tvSeriesDetailData)
            is TvSeriesDetailStatusViewState.Error -> errorHandle(viewState.throwable)
        }

    private fun errorHandle(throwable: Throwable) {
        binding.detailStateLayout.loading()
        Log.e("TvSeriesDetailErrorHandle", throwable.toString())
    }

    private fun displayData(tvSeriesDetailData: TvSeriesDetailData?) {
        Log.e("TvSeriesDetailErrorHandle", "Display Data")
        with(binding) {
            detailStateLayout.content()
            tvSeriesDetailData?.let {
                textViewMovieTitle.text = it.name
                shapeableImageViewMoviePoster.setImage(
                    Constants.BASE_URL_IMAGE + "/" + it.posterPath,
                    createPlaceHolder(requireContext())
                )
                expandTv.text = it.overview
                textViewFirstAirDateValue.text = it.firstAirDate
                textViewLastAirDateValue.text = it.lastAirDate
                textViewVoteAverageValue.text = it.voteAverage.toString()
                textViewVoteCountValue.text = it.voteCount.toString()
                textViewNumberOfEpisodesValue.text = it.numberOfEpisodes.toString()
                textViewNumberOfSeasonsValue.text = it.numberOfSeasons.toString()
                textViewGenresValue.text =
                    it.genres?.joinToString { genreData -> genreData.name.toString() }
            }
        }


    }

    private fun emptyState() {
        binding.detailStateLayout.loading()
        Log.e("TvSeriesDetailErrorHandle", "Empty State")
    }

    private fun loadingInProgress() {
        binding.detailStateLayout.loading()
        Log.e("TvSeriesDetailErrorHandle", "Loading in progress")
    }

    private fun setupView() {
        with(binding) {
            detailStateLayout.loading()
            imageViewBack.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

}
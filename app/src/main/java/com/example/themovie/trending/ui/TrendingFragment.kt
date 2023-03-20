package com.example.themovie.trending.ui


import android.util.Log
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager

import com.example.themovie.common.util.getDeviceLanguage
import com.example.themovie.common.view.BaseFragment
import com.example.themovie.databinding.FragmentTrendingBinding
import com.example.themovie.trending.domain.model.TrendingData
import com.example.themovie.trending.ui.adapter.TrendingAdapter
import com.example.themovie.trending.ui.viewmodel.TrendingViewModel
import com.example.themovie.trending.ui.viewstate.TrendingStatusViewState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TrendingFragment : BaseFragment<FragmentTrendingBinding>() {
    @Inject
    lateinit var trendingAdapter: TrendingAdapter
    private val trendingViewModel: TrendingViewModel by viewModels()
    override fun init() {
        setupView()
        setupViewModel()
    }

    private fun setupViewModel() {
        /*val apiKey = BuildConfig.API_KEY
        with(trendingViewModel){
            getTrending(
                language = getDeviceLanguage(),
                token = "Bearer $apiKey"
            )
            getStateLiveData().observe(viewLifecycleOwner) {
                renderStatusTrendingViewState(it)
            }
        }*/
    }

    private fun renderStatusTrendingViewState(viewState: TrendingStatusViewState) =
        when (viewState) {
            is TrendingStatusViewState.Loading -> loadingInProgress()
            is TrendingStatusViewState.Empty -> emptyState()
            is TrendingStatusViewState.Success -> displayData(viewState.trendingData)
            is TrendingStatusViewState.Error -> errorHandle(viewState.throwable)
        }

    private fun errorHandle(throwable: Throwable) {
        binding.trendingStateLayout.loading()
        Log.e("TrendingFragment", "errorHandle: ${throwable.localizedMessage}")
    }

    private fun displayData(trendingData: TrendingData?) {
        binding.trendingStateLayout.content()
        trendingData?.results?.let { trendingAdapter.setItems(it) }
        Log.e("TrendingFragment", "displayData: ${trendingData?.results}")

    }

    private fun emptyState() {
        binding.trendingStateLayout.loading()
        Log.e("TrendingFragment", "emptyState: ")
    }

    private fun loadingInProgress() {
        binding.trendingStateLayout.loading()
        Log.e("TrendingFragment", "loadingInProgress: ")
    }

    private fun setupView() {
        setUpCitySearchRv()
        with(binding) {
            trendingAdapter.onTrendingClick = {
                if (it.id != null) {
                  //  navigate(TrendingFragmentDirections.actionTrendingFragmentToDetailFragment(it.id!!))
                }
            }
        }
    }

    private fun setUpCitySearchRv() {
        with(binding.trendingRv) {
            layoutManager = GridLayoutManager(context, 2)
            adapter = trendingAdapter
        }
    }

}
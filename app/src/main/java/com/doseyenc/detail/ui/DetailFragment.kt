package com.doseyenc.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.doseyenc.common.BaseFragment
import com.doseyenc.themovie.R
import com.doseyenc.themovie.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.model = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.getMovieDetail(args.id)
        viewModel.movieDetail.observe(viewLifecycleOwner) { movieDetail ->
            binding.movie = movieDetail
        }
        viewModel.navigateToBack.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
        viewModel.navigatePopularMovies.observe(viewLifecycleOwner) {
            findNavController().navigate(
                DetailFragmentDirections.actionDetailFragmentToPopularMoviesFragment(),
                NavOptions.Builder()
                    .setPopUpTo(R.id.detailFragment, true)
                    .build()
            )
        }
        viewModel.showProgress.observe(viewLifecycleOwner, this::showProgress)
        viewModel.errorMessage.observe(viewLifecycleOwner, this::showError)
    }
}
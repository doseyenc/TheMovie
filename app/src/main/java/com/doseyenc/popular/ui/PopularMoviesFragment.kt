package com.doseyenc.popular.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doseyenc.common.BaseFragment
import com.doseyenc.main.ui.adapter.MovieAdapter
import com.doseyenc.themovie.databinding.FragmentPopularMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesFragment : BaseFragment() {

    private lateinit var binding: FragmentPopularMoviesBinding
    private lateinit var movieAdapter: MovieAdapter
    private val viewModel: PopularMoviesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularMoviesBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.popularMovies.observe(viewLifecycleOwner, movieAdapter::submitList)
        viewModel.navigateToDetail.observe(viewLifecycleOwner) { movieId ->
            findNavController().navigate(
                PopularMoviesFragmentDirections.actionPopularMoviesFragmentToDetailFragment(
                    movieId
                )
            )
        }
        viewModel.navigateToBack.observe(viewLifecycleOwner) {
            findNavController().navigate(PopularMoviesFragmentDirections.actionPopularMoviesFragmentToMainFragment())
        }
        viewModel.showProgress.observe(viewLifecycleOwner, this::showProgress)
        viewModel.errorMessage.observe(viewLifecycleOwner, this::showError)
    }

    private fun setupRecyclerView() {
        movieAdapter = MovieAdapter(viewModel)

        binding.recyclerView.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(context)
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItemPosition >= totalItemCount - 5 && viewModel.showProgress.value != true) {
                    viewModel.loadMoreMovies()
                }
            }
        })
    }
}

package com.doseyenc.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doseyenc.common.BaseFragment
import com.doseyenc.main.ui.adapter.CustomAdapter
import com.doseyenc.main.ui.adapter.MovieAdapter
import com.doseyenc.main.ui.adapter.SliderAdapter
import com.doseyenc.themovie.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var customAdapter: CustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapters()
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupViewModel() {

        viewModel.sliderMovies.observe(viewLifecycleOwner, sliderAdapter::submitList)

        viewModel.movies.observe(viewLifecycleOwner, customAdapter::updateMovies)

        viewModel.navigateToDetail.observe(viewLifecycleOwner) { movieId ->
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailFragment(
                    movieId
                )
            )
        }

        viewModel.showProgress.observe(viewLifecycleOwner, this::showProgress)
        viewModel.errorMessage.observe(viewLifecycleOwner, this::showError)
    }

    private fun setupAdapters() {
        sliderAdapter = SliderAdapter(viewModel)
        movieAdapter = MovieAdapter(viewModel)
        customAdapter = CustomAdapter(sliderAdapter, movieAdapter) { movie ->
            viewModel.navigateToDetail(movie)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            adapter = customAdapter
            layoutManager = LinearLayoutManager(context)
        }

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItemPosition >= totalItemCount - 5 && !viewModel.showProgress.value!!) {
                    viewModel.loadMoreMovies()
                }
            }
        })
    }
}
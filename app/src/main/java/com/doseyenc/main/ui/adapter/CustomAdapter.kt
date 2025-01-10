package com.doseyenc.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.doseyenc.main.data.model.Result
import com.doseyenc.themovie.databinding.ListItemMovieBinding
import com.doseyenc.themovie.databinding.ViewpagerItemBinding

class CustomAdapter(
    private val viewPagerAdapter: SliderAdapter,
    private val movieAdapter: MovieAdapter,
    private val onMovieClick: (Result) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_VIEWPAGER = 0
        const val VIEW_TYPE_MOVIE = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_VIEWPAGER else VIEW_TYPE_MOVIE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_VIEWPAGER -> {
                val binding = ViewpagerItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ViewPagerViewHolder(binding)
            }

            VIEW_TYPE_MOVIE -> {
                val binding = ListItemMovieBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                MovieViewHolder(binding, onMovieClick)
            }

            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewPagerViewHolder -> holder.bind(viewPagerAdapter)
            is MovieViewHolder -> {
                val moviePosition = position - 1
                holder.bind(movieAdapter.currentList[moviePosition])
            }
        }
    }

    override fun getItemCount(): Int {
        return movieAdapter.itemCount + 1
    }

    class ViewPagerViewHolder(private val binding: ViewpagerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewPagerAdapter: SliderAdapter) {
            binding.viewPager.adapter = viewPagerAdapter
            binding.springDotsIndicator.attachTo(binding.viewPager)
        }
    }

    class MovieViewHolder(
        private val binding: ListItemMovieBinding,
        private val onMovieClick: (Result) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Result) {
            binding.movie = movie
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                onMovieClick(movie)
            }
        }
    }

    fun updateMovies(movies: List<Result>) {
        movieAdapter.submitList(movies) {
            notifyItemRangeChanged(1, movieAdapter.itemCount)
        }
    }
}


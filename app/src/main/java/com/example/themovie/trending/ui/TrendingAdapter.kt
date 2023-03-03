package com.example.themovie.trending.ui

import android.view.ViewGroup
import com.example.themovie.common.adapter.BaseRecyclerViewAdapter
import com.example.themovie.common.util.createPlaceHolder
import com.example.themovie.common.util.inflate
import com.example.themovie.common.util.setImage
import com.example.themovie.databinding.TrendingRvItemBinding
import com.example.themovie.trending.domain.model.TrendingDataResultsData
import javax.inject.Inject

class TrendingAdapter @Inject constructor() :
    BaseRecyclerViewAdapter<TrendingDataResultsData,
            TrendingRvItemBinding>() {
    var onTrendingClick: ((trending: TrendingDataResultsData) -> Unit)? = null

    inner class TrendingItemViewHolder(private val binding: TrendingRvItemBinding) :
        ViewBindingViewHolder(binding) {
        override fun bind(item: TrendingDataResultsData) {
            with(binding) {
                textViewTrendingName.text = item.title
                textViewVoteAverage.text = "${item.voteAverage}/10"
                imageViewPoster.setImage(
                    url = item.posterPath,
                    createPlaceHolder(context = root.context)
                )
                cardViewTrending.setOnClickListener {
                    onTrendingClick?.invoke(item)
                }
            }
        }
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): ViewBindingViewHolder {
        return TrendingItemViewHolder(parent.inflate(TrendingRvItemBinding::inflate))
    }
}
package com.example.themovie.common.adapter

import androidx.recyclerview.widget.DiffUtil

class DataClassDiffCallback<T : Any>(private val uniqueProperty: (T) -> Any?) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) = uniqueProperty(oldItem) == uniqueProperty(newItem)

    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}
package com.example.themovie.common.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T, VH : RecyclerView.ViewHolder> constructor(
    diffCallback: DiffUtil.ItemCallback<T>
) : ListAdapter<T, VH>(diffCallback) {

    override fun getItemCount(): Int = currentList.size

    fun removeItem(index: Int) {
        val newList = getMutableList()
        newList.removeAt(index)
        submitList(newList)
    }

    fun clearItems() {
        submitList(mutableListOf())
    }

    fun getItems(): List<T> = currentList

    private fun getMutableList(): MutableList<T> = currentList.toMutableList()
}
package com.example.themovie.common.adapter



import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewAdapter<T, VB : ViewBinding> :
    RecyclerView.Adapter<BaseRecyclerViewAdapter<T, VB>.ViewBindingViewHolder>() {

    private var list: List<T> = emptyList()
    private var itemClickListener: ((Int) -> Unit)? = null
    private var itemClickListenerByType: ((T) -> Unit)? = null

    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): ViewBindingViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewBindingViewHolder {
        return getViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: ViewBindingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun getItems(): List<T> = list

    fun setItems(items: List<T>) {
        list = items
        notifyDataSetChanged()
    }

    fun setItemClickListener(listener: ((Int) -> Unit)) {
        this.itemClickListener = listener
    }

    fun setItemClickListenerByType(listener: ((T) -> Unit)) {
        this.itemClickListenerByType = listener
    }

    abstract inner class ViewBindingViewHolder constructor(
        binding: VB
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                if (bindingAdapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener

                itemClickListener?.invoke(bindingAdapterPosition)
                itemClickListenerByType?.invoke(list[bindingAdapterPosition])
            }
        }

        abstract fun bind(item: T)
    }
}

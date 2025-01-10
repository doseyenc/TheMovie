package com.doseyenc.common

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.doseyenc.common.Constants.BASE_URL_IMAGE
import com.doseyenc.themovie.R

object BindingAdapters {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun bindImage(imageView: ImageView, imageUrl: String?) {
        val context = imageView.context

        val placeholder = CircularProgressDrawable(context).apply {
            strokeWidth = 8f
            centerRadius = 40f
            setColorSchemeColors(ContextCompat.getColor(context, R.color.green))
            start()
        }

        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(context)
                .load(BASE_URL_IMAGE + imageUrl)
                .apply(
                    RequestOptions()
                        .placeholder(placeholder)
                        .error(R.drawable.error_template)
                )
                .into(imageView)
        } else {
            imageView.setImageResource(R.drawable.error_template)
        }
    }
}

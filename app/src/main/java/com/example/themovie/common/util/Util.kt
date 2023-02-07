package com.example.themovie.common.util


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.themovie.R
import java.util.*
import java.util.regex.Pattern


object Constants {
    const val BASE_URL_ADDRESS = ""

}

fun goToUrl(url: String): Intent? {
    return Intent(Intent.ACTION_VIEW, Uri.parse(url))
}

fun ImageView.setImage(url: String?, placeholder: CircularProgressDrawable) {
    val options = RequestOptions().placeholder(placeholder).error(R.drawable.baseline_error_24)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}
fun ImageView.setImageWithoutPlaceHolder(url: String?) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun createPlaceHolder(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

fun View.toggleViewVisibility() {
    visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
}





fun isValidEmail(email: CharSequence): Boolean {
    var isValid = true
    val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(email)
    if (!matcher.matches()) {
        isValid = false
    }
    return isValid
}



fun hideKeyboard(activity: Activity) {
    val inputMethodManager = activity.getSystemService(
        Activity.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    if (inputMethodManager.isAcceptingText) {
        inputMethodManager.hideSoftInputFromWindow(
            activity.currentFocus!!.windowToken,
            0
        )
    }
}

fun getDeviceLanguage(): String {
    return Locale.getDefault().language

}












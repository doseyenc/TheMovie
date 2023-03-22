package com.example.themovie

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class TheMovieApplication : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}
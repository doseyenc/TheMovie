package com.doseyenc.detail.di

import com.doseyenc.common.Constants.BASE_URL_ADDRESS
import com.doseyenc.detail.data.source.service.MovieDetailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieDetailModule {

    @Singleton
    @Provides
    fun provideMovieDetailService(): MovieDetailService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(MovieDetailService::class.java)
    }
}

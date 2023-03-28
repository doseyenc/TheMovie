package com.example.themovie.detail.di

import com.example.themovie.common.util.Constants
import com.example.themovie.detail.data.source.MovieDetailDataSource
import com.example.themovie.detail.data.source.remote.MovieDetailRemoteDataSource
import com.example.themovie.detail.data.source.service.MovieDetailService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieDetailModule {

    @Singleton
    @Provides
    fun provideDetailService(): MovieDetailService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(MovieDetailService::class.java)
    }

    @Singleton
    @Provides
    fun provideDetailRemoteDataSourceBuilder(): MovieDetailDataSource.Remote {
        return MovieDetailRemoteDataSource(provideDetailService())
    }

}
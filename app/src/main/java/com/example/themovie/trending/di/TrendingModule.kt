package com.example.themovie.trending.di

import com.example.themovie.common.util.Constants
import com.example.themovie.trending.data.source.TrendingDataSource
import com.example.themovie.trending.data.source.remote.TrendingRemoteDataSource
import com.example.themovie.trending.data.source.service.TrendingService
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
class TrendingModule {

    @Singleton
    @Provides
    fun provideTrendingService(): TrendingService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(TrendingService::class.java)
    }

    @Singleton
    @Provides
    fun provideTrendingRemoteDataSourceBuilder(): TrendingDataSource.Remote {
        return TrendingRemoteDataSource(provideTrendingService())
    }

}
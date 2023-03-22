package com.example.themovie.detail.di

import com.example.themovie.common.util.Constants
import com.example.themovie.detail.data.source.DetailDataSource
import com.example.themovie.detail.data.source.remote.DetailRemoteDataSource
import com.example.themovie.detail.data.source.service.DetailService
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
class DetailModule {

    @Singleton
    @Provides
    fun provideDetailService(): DetailService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(DetailService::class.java)
    }

    @Singleton
    @Provides
    fun provideDetailRemoteDataSourceBuilder(): DetailDataSource.Remote {
        return DetailRemoteDataSource(provideDetailService())
    }

}
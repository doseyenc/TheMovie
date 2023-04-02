package com.example.themovie.search.di

import com.example.themovie.common.util.Constants
import com.example.themovie.search.data.source.SearchDataSource
import com.example.themovie.search.data.source.remote.SearchRemoteDataSource
import com.example.themovie.search.data.source.service.SearchService
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
class SearchModule {
    @Singleton
    @Provides
    fun provideSearchService(): SearchService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(SearchService::class.java)
    }
    @Singleton
    @Provides
    fun provideSearchRemoteDataSourceBuilder(): SearchDataSource.Remote {
        return SearchRemoteDataSource(provideSearchService())
    }

}
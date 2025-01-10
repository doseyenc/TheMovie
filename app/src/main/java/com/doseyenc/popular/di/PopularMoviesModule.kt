package com.doseyenc.popular.di

import com.doseyenc.common.Constants.BASE_URL_ADDRESS
import com.doseyenc.popular.data.source.service.PopularMoviesService
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
class PopularMoviesModule {

    @Singleton
    @Provides
    fun providePopularMovies(): PopularMoviesService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(PopularMoviesService::class.java)
    }
}

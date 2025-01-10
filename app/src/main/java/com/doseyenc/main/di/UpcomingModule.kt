package com.doseyenc.main.di

import com.doseyenc.common.Constants.BASE_URL_ADDRESS
import com.doseyenc.main.data.source.service.UpcomingService
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
class UpcomingModule {

    @Singleton
    @Provides
    fun provideUpcomingService(): UpcomingService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(UpcomingService::class.java)
    }
}

package com.example.lomakincountriesapp.di.arts

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import services.ArtsService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ArtsModule {

    @Singleton
    @Provides
    fun provideArtsService(): ArtsService {
        val baseUrl = "https://api.artic.edu/api/v1/"
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
        return retrofit.create(ArtsService::class.java)
    }
}
package com.example.lomakincountriesapp.di.arts

import com.example.lomakincountriesapp.network.ArtsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ArtsModule {
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
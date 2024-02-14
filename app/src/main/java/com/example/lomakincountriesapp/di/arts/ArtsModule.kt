package com.example.lomakincountriesapp.di.arts

import com.example.data.room.ArtRepositoryImpl
import com.example.data.room.ArtsRoomEntity
import com.example.data.room.ArtsService
import com.example.data.room.Mapper
import com.example.data.room.RoomArtsMapper
import com.example.domain.ArtsDomainEntity
import com.example.domain.ArtsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Singleton
    @Provides
    fun provideArtsRepository(impl : ArtRepositoryImpl): ArtsRepository {
        return impl
    }

    @Singleton
    @Provides
    fun provideMapper(map: RoomArtsMapper): Mapper<ArtsRoomEntity, ArtsDomainEntity> {
        return map
    }
}
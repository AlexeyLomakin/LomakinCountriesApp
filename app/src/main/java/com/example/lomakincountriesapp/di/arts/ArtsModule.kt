package com.example.lomakincountriesapp.di.arts

import com.example.data.room.Mapper
import com.example.data.room.RoomArtsMapper
import com.example.data.room.arts.ArtRepositoryImpl
import com.example.data.room.arts.ArtsRoomEntity
import com.example.data.room.arts.ArtsService
import com.example.domain.arts.ArtsDomainEntity
import com.example.domain.arts.ArtsRepository
import com.example.domain.arts.usecases.GetAllArtsUseCase
import com.example.domain.arts.usecases.GetAllArtsUseCaseImpl
import com.example.domain.arts.usecases.SaveAllArtsUseCase
import com.example.domain.arts.usecases.SaveAllArtsUseCaseImpl
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

    @Provides
    fun provideGetAllArtsUseCase(getAllArts: GetAllArtsUseCaseImpl): GetAllArtsUseCase {
        return getAllArts
    }

    @Provides
    fun provideSaveAllArtsUseCase(saveAllArts: SaveAllArtsUseCaseImpl): SaveAllArtsUseCase {
        return saveAllArts
    }
}
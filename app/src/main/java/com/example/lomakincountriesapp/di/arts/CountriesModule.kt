package com.example.lomakincountriesapp.di.arts

import com.example.data.room.Mapper
import com.example.data.room.RoomCountriesMapper
import com.example.data.room.countries.CountriesRepositoryImpl
import com.example.data.room.countries.CountriesRoomEntity
import com.example.data.room.countries.CountriesService
import com.example.domain.countries.CountriesDomainEntity
import com.example.domain.countries.CountriesRepository
import com.example.domain.countries.usecases.GetAllCountriesUseCase
import com.example.domain.countries.usecases.GetAllCountriesUseCaseImpl
import com.example.domain.countries.usecases.GetCountryByNameUseCase
import com.example.domain.countries.usecases.GetCountryByNameUseCaseImpl
import com.example.domain.countries.usecases.SaveAllCountriesUseCase
import com.example.domain.countries.usecases.SaveAllCountriesUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CountriesModule {
    @Singleton
    @Provides
    fun provideCountriesService(): CountriesService {
        val baseUrl = "https://restcountries.com/v3.1/"
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
        return retrofit.create(CountriesService::class.java)
    }
    @Singleton
    @Provides
    fun provideCountriesRepository(impl : CountriesRepositoryImpl): CountriesRepository {
        return impl
    }

    @Singleton
    @Provides
    fun provideMapper(map: RoomCountriesMapper): Mapper<CountriesRoomEntity, CountriesDomainEntity> {
        return map
    }

    @Provides
    fun provideGetAllCountriesUseCase(getAllCountries: GetAllCountriesUseCaseImpl): GetAllCountriesUseCase {
        return getAllCountries
    }

    @Provides
    fun provideSaveAllCountriesUseCase(saveAllCountries: SaveAllCountriesUseCaseImpl): SaveAllCountriesUseCase {
        return saveAllCountries
    }

    @Provides
    fun  getCountryByName(getCountryByName: GetCountryByNameUseCaseImpl): GetCountryByNameUseCase {
        return getCountryByName
    }
}
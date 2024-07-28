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
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CountriesModule {

    @Binds
    abstract fun bindCountriesRepository(impl: CountriesRepositoryImpl): CountriesRepository

    @Binds
    abstract fun bindMapper(map: RoomCountriesMapper): Mapper<CountriesRoomEntity, CountriesDomainEntity>

    @Binds
    abstract fun bindGetAllCountriesUseCase(getAllCountries: GetAllCountriesUseCaseImpl): GetAllCountriesUseCase

    @Binds
    abstract fun bindSaveAllCountriesUseCase(saveAllCountries: SaveAllCountriesUseCaseImpl): SaveAllCountriesUseCase

    @Binds
    abstract fun bindGetCountryByNameUseCase(getCountryByName: GetCountryByNameUseCaseImpl): GetCountryByNameUseCase

    companion object {

        @Singleton
        @Provides
        fun provideCountriesService(retrofit: Retrofit): CountriesService {
            return retrofit.create(CountriesService::class.java)
        }

        @Singleton
        @Provides
        fun provideRetrofit(): Retrofit {
            val baseUrl = "https://restcountries.com/v3.1/"
            val logging = HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(client)
                .build()
        }
    }
}


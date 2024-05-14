package com.example.lomakincountriesapp.di.arts

import android.content.Context
import androidx.room.Room
import com.example.data.room.countries.CountriesDao
import com.example.data.room.countries.CountriesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountriesDatabaseModule {
    @Singleton
    @Provides
    fun provideCountriesDatabase(@ApplicationContext context: Context): CountriesDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CountriesDatabase::class.java,
            "countries_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideCountriesDao(countriesDatabase: CountriesDatabase): CountriesDao {
        return countriesDatabase.countriesDatabaseDao
    }
}
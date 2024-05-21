package com.example.domain.countries

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {
    suspend fun saveAllCountries()
    fun getAllCountries(): LiveData<List<CountriesDomainEntity>>

    fun getCountryByName(name: String): LiveData<CountriesDomainEntity>

    fun getCountriesPaged(): Flow<PagingData<CountriesDomainEntity>>
}





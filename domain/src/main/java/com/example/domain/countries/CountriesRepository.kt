package com.example.domain.countries

import androidx.lifecycle.LiveData

interface CountriesRepository {
    suspend fun saveAllCountries()
    fun getAllCountries(): LiveData<List<CountriesDomainEntity>>

    fun getCountryByName(name: String): LiveData<CountriesDomainEntity>
}





package com.example.domain.countries.usecases

import androidx.lifecycle.LiveData
import com.example.domain.countries.CountriesDomainEntity
import com.example.domain.countries.CountriesRepository
import javax.inject.Inject

class GetCountryByNameUseCaseImpl @Inject constructor(
    private val countriesRepository: CountriesRepository
) : GetCountryByNameUseCase {
    override suspend fun invoke(name: String): LiveData<CountriesDomainEntity> {
        return countriesRepository.getCountryByName(name)
    }
}

interface GetCountryByNameUseCase {
    suspend operator fun invoke(name: String) : LiveData<CountriesDomainEntity>
}
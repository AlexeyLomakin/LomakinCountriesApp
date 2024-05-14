package com.example.domain.countries.usecases

import androidx.lifecycle.LiveData
import com.example.domain.countries.CountriesDomainEntity
import com.example.domain.countries.CountriesRepository
import javax.inject.Inject

class GetAllCountriesUseCaseImpl @Inject constructor(
    private val countriesRepository: CountriesRepository
) : GetAllCountriesUseCase {

    override fun invoke() : LiveData<List<CountriesDomainEntity>> {
        return countriesRepository.getAllCountries()
    }
}

interface GetAllCountriesUseCase {
    operator fun invoke() : LiveData<List<CountriesDomainEntity>>

}
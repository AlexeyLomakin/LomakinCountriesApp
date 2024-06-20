package com.example.domain.countries.usecases

import com.example.domain.countries.CountriesRepository
import javax.inject.Inject

class SaveAllCountriesUseCaseImpl @Inject constructor(
    private val countriesRepository: CountriesRepository
) : SaveAllCountriesUseCase {

    override suspend fun invoke() {
        countriesRepository.saveAllCountries()
    }
}

interface SaveAllCountriesUseCase {
    suspend operator fun invoke()
}


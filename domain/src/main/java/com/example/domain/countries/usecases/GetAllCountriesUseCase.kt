package com.example.domain.countries.usecases

import androidx.paging.PagingData
import com.example.domain.countries.CountriesDomainEntity
import com.example.domain.countries.CountriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCountriesUseCaseImpl @Inject constructor(
    private val countriesRepository: CountriesRepository
) : GetAllCountriesUseCase {

    override fun invoke(): Flow<PagingData<CountriesDomainEntity>> {
        return countriesRepository.getCountriesPaged()
    }
}

interface GetAllCountriesUseCase {
    operator fun invoke(): Flow<PagingData<CountriesDomainEntity>>
}
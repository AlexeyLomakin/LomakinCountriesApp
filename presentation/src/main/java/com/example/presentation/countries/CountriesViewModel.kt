package com.example.presentation.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.domain.countries.CountriesDomainEntity
import com.example.domain.countries.usecases.GetAllCountriesUseCase
import com.example.domain.countries.usecases.SaveAllCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    getAllCountriesUseCase: GetAllCountriesUseCase,
    private val saveAllCountriesUseCase: SaveAllCountriesUseCase
): ViewModel(){
    val countriesData: LiveData<List<CountriesDomainEntity>> = getAllCountriesUseCase()
    init {
        loadPages()
    }
    private fun loadPages() {
        saveAllCountriesUseCase
    }
}
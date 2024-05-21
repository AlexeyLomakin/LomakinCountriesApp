package com.example.presentation.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.countries.CountriesDomainEntity
import com.example.domain.countries.usecases.GetAllCountriesUseCase
import com.example.domain.countries.usecases.SaveAllCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val saveAllCountriesUseCase: SaveAllCountriesUseCase
) : ViewModel() {

    val countriesData: LiveData<PagingData<CountriesDomainEntity>> =
        getAllCountriesUseCase().cachedIn(viewModelScope).asLiveData()

    private val _isMaxCountries = MutableLiveData<Boolean>()
    val isMaxCountries: LiveData<Boolean> = _isMaxCountries

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadAllCountries()
        }
        _isMaxCountries.value = false
    }

    fun onPageFinished() {
        _isMaxCountries.value = true
    }

    private suspend fun loadAllCountries() {
        _loading.postValue(true)
        _error.postValue(null) // Сброс ошибки перед новой загрузкой

        try {
            saveAllCountriesUseCase()
        } catch (e: Exception) {
            _error.postValue("Failed to load data: ${e.message}")
        } finally {
            _loading.postValue(false)
        }
    }
}




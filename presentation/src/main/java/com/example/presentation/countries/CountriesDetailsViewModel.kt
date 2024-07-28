package com.example.presentation.countries

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.countries.CountriesDomainEntity
import com.example.domain.countries.usecases.GetCountryByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class CountriesDetailsViewModel @Inject constructor(
    private val getCountryByNameUseCase: GetCountryByNameUseCase
) : ViewModel() {

    private val _country = MutableLiveData<CountriesDomainEntity>()
    val country: LiveData<CountriesDomainEntity> get() = _country

    private val _countryFlag = MutableLiveData<Drawable>()
    val countryFlag: LiveData<Drawable> get() = _countryFlag

    fun loadCountryByName(name: String) {
        viewModelScope.launch {
            val country = getCountryByNameUseCase(name).value
            _country.postValue(country!!)
            loadCountryFlag(country.flags.toString())
        }
    }

    private suspend fun loadCountryFlag(url: String) {
        withContext(Dispatchers.IO) {
                val inputStream = URL(url).openStream()
                val drawable = Drawable.createFromStream(inputStream, "src")
                _countryFlag.postValue(drawable!!)
        }
    }
}


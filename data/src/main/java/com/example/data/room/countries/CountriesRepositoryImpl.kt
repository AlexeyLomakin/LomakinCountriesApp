package com.example.data.room.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.data.room.Mapper
import com.example.domain.countries.CountriesDomainEntity
import com.example.domain.countries.CountriesRepository
import timber.log.Timber
import javax.inject.Inject

class CountriesRepositoryImpl @Inject constructor(
    private val countriesService: CountriesService,
    private val countriesDao: CountriesDao,
    private val mapper: Mapper<CountriesRoomEntity, CountriesDomainEntity>
): CountriesRepository {
    override suspend fun saveAllCountries() {
        try {
            val response = countriesService.getAllCountries().body()
            val countriesRoomEntityList = response?.map { list ->
                CountriesRoomEntity(
                    name = list.name?.official.toString(),
                    area = list.area,
                    population = list.population,
                    languages = list.languages.toString(),
                    capital = list.capital.toString(),
                    flags = list.flags?.png.toString(),
                )
            }
            if (!countriesRoomEntityList.isNullOrEmpty()) {
                countriesDao.insertCountries(countriesRoomEntityList)
                Timber.d("added ${response.size} countries")
            } else {
                Timber.d("Response is empty")

            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override fun getAllCountries(): LiveData<List<CountriesDomainEntity>> {
        return countriesDao.getAllCountries().map { list ->
            list.map { entity ->
                mapper.map(entity)
            }

        }
    }

    override fun getCountryByName(name: String): LiveData<CountriesDomainEntity> {
        return countriesDao.getCountryByName(name)
    }
}
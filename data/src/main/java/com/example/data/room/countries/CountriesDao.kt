package com.example.data.room.countries

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.countries.CountriesDomainEntity

@Dao
interface CountriesDao {
    @Query("select * from countries_database")
    fun getAllCountries(): LiveData<List<CountriesRoomEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountries(countries: List<CountriesRoomEntity>)

    @Query("select * from countries_database where name = :name")
    fun getCountryByName(name: String): LiveData<CountriesDomainEntity>

    @Query("SELECT * FROM countries_database")
    fun getAllCountriesPaged(): PagingSource<Int, CountriesRoomEntity>
}


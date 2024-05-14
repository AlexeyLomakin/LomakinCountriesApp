package com.example.data.room.countries

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CountriesRoomEntity::class], version = 1, exportSchema = false)
abstract class CountriesDatabase: RoomDatabase() {
    abstract val countriesDatabaseDao: CountriesDao
}

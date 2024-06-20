package com.example.data.room.countries

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [CountriesRoomEntity::class], version = 2, exportSchema = false)
@TypeConverters(Convertors::class)
abstract class CountriesDatabase: RoomDatabase() {
    abstract val countriesDatabaseDao: CountriesDao
}

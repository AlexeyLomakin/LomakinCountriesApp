package com.example.lomakincountriesapp.presentation.arts

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lomakincountriesapp.data.arts.ArtsEntity

@Database(entities = [ArtsEntity::class], version = 2, exportSchema = false)
abstract class ArtsDatabase : RoomDatabase() {
    abstract val artsDatabaseDao: ArtsDao
}
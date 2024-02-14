package com.example.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.room.ArtsDao
import com.example.data.room.ArtsEntity


@Database(entities = [ArtsEntity::class], version = 2, exportSchema = false)
abstract class ArtsDatabase : RoomDatabase() {
    abstract val artsDatabaseDao: ArtsDao
}
package com.example.data.room.arts

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ArtsRoomEntity::class], version = 3, exportSchema = false)
abstract class ArtsDatabase : RoomDatabase() {
    abstract val artsDatabaseDao: ArtsDao
}
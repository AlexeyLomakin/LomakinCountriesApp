package com.example.data.room.arts

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArtsDao {

    @Query("select * from arts_database")
    fun getAllArts(): LiveData<List<ArtsRoomEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArt(arts: List<ArtsRoomEntity>)
}
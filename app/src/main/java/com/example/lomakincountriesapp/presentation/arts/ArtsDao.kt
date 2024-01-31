package com.example.lomakincountriesapp.presentation.arts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lomakincountriesapp.data.arts.ArtsEntity

@Dao
interface ArtsDao {

    @Query("select * from arts_database")
    fun getAllArts(): List<ArtsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArt(arts: List<ArtsEntity>)
}
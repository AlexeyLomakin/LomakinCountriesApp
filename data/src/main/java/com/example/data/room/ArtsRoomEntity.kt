package com.example.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "arts_database")
data class ArtsRoomEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "art_id")
    var artId: Long? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "artist")
    var artistDisplay: String? = null,

    @ColumnInfo(name = "image_url")
    var imageUrl: String? = null,

    @ColumnInfo(name = "total_page")
    var totalPage: Int? = null,

    @ColumnInfo(name = "current_page")
    var currentPage: Int? = null
)
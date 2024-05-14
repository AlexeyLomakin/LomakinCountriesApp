package com.example.data.room.countries

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity(tableName = "countries_database")
data class CountriesRoomEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "capital")
    var capital: String? = null,

    @ColumnInfo(name = "languages")
    var languages: String? = null,

    @ColumnInfo(name = "flags")
    var flags: String? = null,

    @ColumnInfo(name = "area")
    var area: BigDecimal? = null,

    @ColumnInfo(name = "population")
    var population: ULong? = null
)

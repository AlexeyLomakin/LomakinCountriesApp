package com.example.data.room.countries

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.math.BigDecimal

class Convertors {

    private val gson = Gson()

    @TypeConverter
    fun fromMapToString(map: Map<String?, String?>?): String? {
        return gson.toJson(map)
    }

    @TypeConverter
    fun fromStringToMap(value: String?): Map<String?, String?>? {
        val mapType = object : TypeToken<Map<String?, String?>?>() {}.type
        return gson.fromJson(value, mapType)
    }
    @TypeConverter
    fun fromBigDecimalToString(value: BigDecimal?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun fromStringToBigDecimal(value: String?): BigDecimal? {
        return value?.let {
            BigDecimal(it)
        }
    }

    @TypeConverter
    fun fromListToString(value: List<String>?): String? {
        return value?.joinToString(",")
    }

    @TypeConverter
    fun fromStringToList(value: String?): List<String>? {
        return value?.split(",")
    }

    @TypeConverter
    fun fromULongToString(value: ULong?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun fromStringToULong(value: String?): ULong? {
        return value?.toULong()?.toULong()
    }
}
package com.example.practice.data.db

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun fromIds(ids: List<Int>): String {
        return ids.joinToString(",")
    }

    @TypeConverter
    fun fromString(data: String): List<Int> {
        return data.split(",").toList().map { it.toInt() }
    }

    @TypeConverter
    fun fromStringList(data: List<String>): String {
        return data.joinToString(",")
    }

    @TypeConverter
    fun fromStringToStringList(data: String): List<String> {
        return data.split(",").toList()
    }
}

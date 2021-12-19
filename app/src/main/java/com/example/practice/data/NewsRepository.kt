package com.example.practice.data

import android.content.Context
import com.example.practice.model.News
import com.example.practice.utils.readAssetFileToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class NewsRepository(private val context: Context) {
    fun getNewsAll(): List<News> {
        val data = readAssetFileToString(context, "news.json")
        return Json.decodeFromString(data)
    }

    fun getNewsById(id: Int): News {
        return getNewsAll().find { news -> news.id == id }!!
    }
}

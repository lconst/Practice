package com.example.practice.data

import android.content.Context
import com.example.practice.model.News
import com.example.practice.utils.NEWS_JSON_FILE_NAME
import com.example.practice.utils.readAssetFileToString
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class NewsRepository(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getNewsAllSuspend(): List<News> = withContext(ioDispatcher) {
        val data = readAssetFileToString(context, NEWS_JSON_FILE_NAME)
        Json.decodeFromString(data)
    }

    suspend fun getNewsByIdSuspend(id: Int): News {
        return getNewsAllSuspend().find { news -> news.id == id }!!
    }

    fun getNewsAll(): List<News> {
        val data = readAssetFileToString(context, NEWS_JSON_FILE_NAME)
        return Json.decodeFromString(data)
    }

    fun getNewsById(id: Int): News {
        return getNewsAll().find { news -> news.id == id }!!
    }
}

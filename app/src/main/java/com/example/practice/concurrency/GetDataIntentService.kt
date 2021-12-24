package com.example.practice.concurrency

import android.app.IntentService
import android.content.Context
import android.content.Intent
import com.example.practice.PracticeApp
import com.example.practice.model.Category
import com.example.practice.model.News
import com.example.practice.utils.*
import kotlinx.serialization.decodeFromString
import timber.log.Timber

class GetDataIntentService :
    IntentService(GetDataIntentService::javaClass.name) {

    override fun onHandleIntent(intent: Intent?) {
        var newsList = arrayListOf<News>()
        var categoryList = arrayListOf<Category>()
        val dataType = intent?.getIntExtra(DATA_TYPE_KEY, 0) ?: 0
        try {
            Timber.d("Start get data service")
            Thread.sleep(SLEEP_TIME)
            when (dataType) {
                DATA_TYPE_CATEGORIES -> {
                    categoryList = getCategories()
                }
                DATA_TYPE_NEWS -> {
                    val data = readAssetFileToString(this, NEWS_JSON_FILE_NAME)
                    newsList = jsonFormat.decodeFromString(data)
                }
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        val resultIntent = Intent()
        resultIntent.action = ACTION_GET_DATA
        resultIntent.addCategory(Intent.CATEGORY_DEFAULT)
        when (dataType) {
            DATA_TYPE_CATEGORIES -> {
                resultIntent.apply {
                    putExtra(DATA_TYPE_KEY, DATA_TYPE_CATEGORIES)
                    putExtra(RESPONSE_GET_DATA_KEY, categoryList)
                }
            }
            DATA_TYPE_NEWS -> {
                resultIntent.apply {
                    putExtra(DATA_TYPE_KEY, DATA_TYPE_NEWS)
                    putExtra(RESPONSE_GET_DATA_KEY, newsList)
                }
            }
        }
        sendBroadcast(resultIntent)
        stopSelf()
        Timber.d("Stop service")
    }

    private fun getCategories(): ArrayList<Category> {
        val data = readAssetFileToString(this, CATEGORIES_JSON_FILE_NAME)
        val categories = jsonFormat.decodeFromString<ArrayList<Category>>(data)
        val sharedPref =
            this.getSharedPreferences(PracticeApp.APP_PREFERENCES, Context.MODE_PRIVATE)
        sharedPref?.let {
            categories.forEach { category ->
                category.isEnabled =
                    sharedPref.getBoolean(category.name + "id ${category.id}", true)
            }
        }
        return categories
    }

    companion object {
        const val ACTION_GET_DATA = "GetDataIntentService.RESPONSE"
        const val RESPONSE_GET_DATA_KEY = "response_key"
        const val DATA_TYPE_KEY = "data_type_key"
        const val DATA_TYPE_CATEGORIES = 1
        const val DATA_TYPE_NEWS = 2
    }
}

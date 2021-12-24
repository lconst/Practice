package com.example.practice.data

import android.content.Context
import com.example.practice.PracticeApp.Companion.APP_PREFERENCES
import com.example.practice.model.Category
import com.example.practice.utils.CATEGORIES_JSON_FILE_NAME
import com.example.practice.utils.jsonFormat
import com.example.practice.utils.readAssetFileToString
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString

class CategoryRepository(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun getCategoriesSuspend(): List<Category> = withContext(ioDispatcher) {
        val data = readAssetFileToString(context, CATEGORIES_JSON_FILE_NAME)
        val categories = jsonFormat.decodeFromString<List<Category>>(data)
        val sharedPref = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        sharedPref?.let {
            categories.forEach { category ->
                category.isEnabled =
                    sharedPref.getBoolean(category.name + "id ${category.id}", true)
            }
        }
        categories
    }

    fun getCategories(): List<Category> {
        val data = readAssetFileToString(context, CATEGORIES_JSON_FILE_NAME)
        val categories = jsonFormat.decodeFromString<List<Category>>(data)
        val sharedPref = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        sharedPref?.let {
            categories.forEach { category ->
                category.isEnabled =
                    sharedPref.getBoolean(category.name + "id ${category.id}", true)
            }
        }
        return categories
    }
}

package com.example.practice.data

import android.content.Context
import com.example.practice.PracticeApp.Companion.APP_PREFERENCES
import com.example.practice.model.Category
import com.example.practice.utils.readAssetFileToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CategoryRepository(private val context: Context) {

    private val jsonFormat = Json { ignoreUnknownKeys = true }

    fun getCategories(): List<Category> {
        val data = readAssetFileToString(context, "categories.json")
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

package com.example.practice.data

import android.content.Context
import com.example.practice.PracticeApp.Companion.APP_PREFERENCES
import com.example.practice.model.Category
import com.example.practice.utils.CATEGORIES_JSON_FILE_NAME
import com.example.practice.utils.jsonFormat
import com.example.practice.utils.readAssetFileToString
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.serialization.decodeFromString

class CategoryRepository(private val context: Context) {

    private var cachedCategory: List<Category> = listOf()
    val categoryChanged: PublishSubject<Boolean> = PublishSubject.create()

    fun getCategories(): Single<List<Category>> {
        return if (cachedCategory.isEmpty()) {
            Single.fromCallable {
                val data = readAssetFileToString(context, CATEGORIES_JSON_FILE_NAME)
                val categories = jsonFormat.decodeFromString<List<Category>>(data)
                val sharedPref = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
                sharedPref?.let {
                    categories.forEach { category ->
                        category.isEnabled =
                            sharedPref.getBoolean(category.name + "id ${category.id}", true)
                    }
                }
                cachedCategory = categories
                categoryChanged.onNext(true)
                cachedCategory
            }
        } else {
            Single.just(cachedCategory)
        }
    }

    fun update(categories: List<Category>) {
        cachedCategory = categories
        categoryChanged.onNext(true)
    }
}

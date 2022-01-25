package com.example.practice.data

import android.content.Context
import com.example.practice.PracticeApp.Companion.APP_PREFERENCES
import com.example.practice.data.db.AppDatabase
import com.example.practice.model.Category
import com.example.practice.utils.CATEGORIES_JSON_FILE_NAME
import com.example.practice.utils.jsonFormat
import com.example.practice.utils.readAssetFileToString
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import timber.log.Timber

@ExperimentalSerializationApi
class CategoryRepository(
    private val context: Context,
    private val database: AppDatabase
) {

    val categoryChanged: PublishSubject<Boolean> = PublishSubject.create()
    private var firstSession = true

    fun getCategories(): Observable<List<Category>> {
        return if (firstSession) {
            Single.concat(getFromDB(), getFromFile())
                .toObservable()
                .doOnComplete { firstSession = false }
        } else {
            getFromDB().toObservable()
        }
    }

    private fun getFromFile(): Single<List<Category>> {
        return Single.fromCallable {
            Timber.d("getFromFile ")
            val data = readAssetFileToString(context, CATEGORIES_JSON_FILE_NAME)
            val categories = jsonFormat.decodeFromString<List<Category>>(data)
            val sharedPref = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
            sharedPref?.let {
                categories.forEach { category ->
                    category.isEnabled =
                        sharedPref.getBoolean(category.name + "id ${category.id}", true)
                }
            }
            saveInDB(categories)
            categories
        }
    }

    private fun getFromDB(): Single<List<Category>> {
        Timber.d("getFromDB ")
        return database.categoryDao().getAll()
    }

    private fun saveInDB(categories: List<Category>) {
        Timber.d("saveInDB ${categories.size}")
        database.categoryDao().insert(categories)
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun update(categories: List<Category>) {
        saveInDB(categories)
        categoryChanged.onNext(true)
    }
}

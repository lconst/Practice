package com.example.practice

import android.app.Application
import com.example.practice.data.CategoryRepository
import com.example.practice.data.NewsRepository
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber

class PracticeApp : Application() {

    val newsRepository by lazy { NewsRepository(this) }
    val categoryRepository by lazy { CategoryRepository(this) }

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        AndroidThreeTen.init(this)
    }

    companion object {
        lateinit var instance: PracticeApp
            private set
        const val APP_PREFERENCES = "PracticePreferences"
    }
}

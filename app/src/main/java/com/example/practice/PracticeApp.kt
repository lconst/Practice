package com.example.practice

import android.app.Application
import androidx.room.Room
import com.example.practice.data.CategoryRepository
import com.example.practice.data.NewsRepository
import com.example.practice.data.db.AppDatabase
import com.example.practice.data.db.mappers.NewsToEntityMapper
import com.example.practice.data.db.mappers.NewsToFollowerMapper
import com.example.practice.data.db.mappers.NewsToModelMapper
import com.jakewharton.threetenabp.AndroidThreeTen
import kotlinx.serialization.ExperimentalSerializationApi
import timber.log.Timber

@ExperimentalSerializationApi
class PracticeApp : Application() {

    val newsRepository by lazy {
        NewsRepository(
            this,
            db.newsDao(),
            NewsToEntityMapper(),
            NewsToFollowerMapper(),
            NewsToModelMapper()
        )
    }
    val categoryRepository by lazy { CategoryRepository(this, db) }

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, DATABASE_NAME
        ).build()
    }

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
        private const val DATABASE_NAME = "database-help"
    }
}

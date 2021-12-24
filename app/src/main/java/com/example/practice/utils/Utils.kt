package com.example.practice.utils

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.serialization.json.Json

fun AppCompatActivity.setupToolbar(toolbar: Toolbar) {
    this.setSupportActionBar(toolbar)
    this.supportActionBar?.setDisplayShowTitleEnabled(false)
}

fun readAssetFileToString(context: Context, fileName: String): String {
    val stream = context.assets.open(fileName)
    return stream.bufferedReader().readText()
}

fun ImageView.setImageByResourceName(name: String) {
    this.setImageResource(
        context.resources.getIdentifier(
            name,
            "drawable",
            context.packageName
        )
    )
}

val jsonFormat = Json { ignoreUnknownKeys = true }

const val SLEEP_TIME = 5000L
const val CONCURRENT_MODE_COROUTINES = 0
const val CONCURRENT_MODE_ASYNC_TASK = 1
const val CONCURRENT_MODE_EXECUTOR = 2
const val CONCURRENT_MODE_INTENT_SERVICE = 3
const val CONCURRENT_MODE = CONCURRENT_MODE_COROUTINES
const val NEWS_JSON_FILE_NAME = "news.json"
const val CATEGORIES_JSON_FILE_NAME = "categories.json"

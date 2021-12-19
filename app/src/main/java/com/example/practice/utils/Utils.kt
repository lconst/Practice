package com.example.practice.utils

import android.content.Context
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

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

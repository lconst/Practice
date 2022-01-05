package com.example.practice.data

import android.content.Context
import com.example.practice.model.News
import com.example.practice.utils.NEWS_JSON_FILE_NAME
import com.example.practice.utils.readAssetFileToString
import io.reactivex.rxjava3.core.Single
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class NewsRepository(private val context: Context) {

    private var cachedNews: ArrayList<News> = arrayListOf()

    fun getNewsAllObservable(): Single<List<News>> {
        return if (cachedNews.isEmpty()) {
            Single.fromCallable { readAssetFileToString(context, NEWS_JSON_FILE_NAME) }
                .map { data ->
                    cachedNews = Json.decodeFromString(data)
                    cachedNews
                }
        } else {
            Single.just(cachedNews)
        }
    }

    fun getNewsById(id: Int): Single<News> {
        return if (cachedNews.isEmpty()) {
            getNewsAllObservable()
                .flatMap { list -> Single.just(list.find { news -> id == news.id }) }
        } else {
            Single.just(cachedNews.first { news -> id == news.id })
        }
    }

    fun update(news: News) {
        news.isRead = true
        val oldNewsIndex = cachedNews.indexOfFirst { it.id == news.id }
        cachedNews[oldNewsIndex] = news
    }
}

package com.example.practice.data

import android.content.Context
import com.example.practice.data.db.NewsDao
import com.example.practice.data.db.entity.NewsFollowersCrossRef
import com.example.practice.data.db.mappers.NewsToEntityMapper
import com.example.practice.data.db.mappers.NewsToFollowerMapper
import com.example.practice.data.db.mappers.NewsToModelMapper
import com.example.practice.model.News
import com.example.practice.utils.NEWS_JSON_FILE_NAME
import com.example.practice.utils.readAssetFileToString
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import timber.log.Timber

@ExperimentalSerializationApi
class NewsRepository(
    private val context: Context,
    private val newsDao: NewsDao,
    private val newsModelToEntityMapper: NewsToEntityMapper,
    private val followerModelToEntityMapper: NewsToFollowerMapper,
    private val newsToModelMapper: NewsToModelMapper
) {
    private var firstSession = true

    fun getNewsAll(): Observable<List<News>> {
        return if (firstSession) {
            Single.concat(getFromDB(), getFromFile())
                .toObservable()
                .doOnComplete { firstSession = false }
        } else {
            getFromDB().toObservable()
        }
    }

    fun getNewsById(id: Int): Observable<News> {
        return if (firstSession) {
            getNewsAll()
                .flatMap { list -> Observable.fromIterable(list) }
                .filter { news -> news.id == id }
        } else {
            getFromDB(id).toObservable()
        }
    }

    fun update(news: News) {
        news.isRead = true
        newsDao.update(newsModelToEntityMapper.mapFrom(news))
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    private fun getFromFile(): Single<List<News>> {
        return Single.fromCallable {
            Timber.d("getFromFile")
            val data = readAssetFileToString(context, NEWS_JSON_FILE_NAME)
            val news = Json.decodeFromString<List<News>>(data)
            saveInDB(news)
            news
        }
    }

    private fun getFromDB(): Single<List<News>> {
        Timber.d("getFromDB")
        return newsDao.getAll()
            .map { newsList -> newsList.map { newsToModelMapper.mapFrom(it) } }
    }

    private fun getFromDB(id: Int): Single<News> {
        return newsDao.getById(id)
            .map { newsToModelMapper.mapFrom(it) }
    }

    private fun saveInDB(news: List<News>) {
        newsDao.insertNews(news.map { newsModelToEntityMapper.mapFrom(it) })
            .andThen(
                Observable.fromIterable(news)
                    .flatMapCompletable {
                        newsDao.insertFollowers(followerModelToEntityMapper.mapFrom(it))
                    }
            )
            .andThen(
                Observable.fromIterable(news)
                    .flatMapCompletable { newsItem ->
                        Observable.fromIterable(newsItem.followers)
                            .flatMapCompletable { follower ->
                                newsDao.insertNewsFollowersCrossRef(
                                    NewsFollowersCrossRef(newsItem.id, follower.id)
                                )
                            }
                    }
            )
            .subscribeOn(Schedulers.io())
            .subscribe({
                Timber.d("Save in DB")
            }, { error ->
                Timber.e("$error")
            })
    }
}

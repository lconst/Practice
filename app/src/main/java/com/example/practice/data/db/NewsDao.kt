package com.example.practice.data.db

import androidx.room.Dao
import androidx.room.Transaction
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Update
import androidx.room.OnConflictStrategy.REPLACE
import com.example.practice.data.db.entity.FollowerEntity
import com.example.practice.data.db.entity.NewsEntity
import com.example.practice.data.db.entity.NewsFollowersCrossRef
import com.example.practice.data.db.relations.NewsWithFollowers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface NewsDao {
    @Transaction
    @Query("SELECT * from news")
    fun getAll(): Single<List<NewsWithFollowers>>

    @Query("SELECT * from news WHERE newsId = :id")
    fun getById(id: Int): Single<NewsWithFollowers>

    @Insert(onConflict = REPLACE)
    fun insertNews(news: List<NewsEntity>): Completable

    @Insert(onConflict = REPLACE)
    fun insertFollowers(followers: List<FollowerEntity>): Completable

    @Insert(onConflict = REPLACE)
    fun insertNewsFollowersCrossRef(newsFollowersCrossRef: NewsFollowersCrossRef): Completable

    @Update(onConflict = REPLACE)
    fun update(news: NewsEntity): Completable
}

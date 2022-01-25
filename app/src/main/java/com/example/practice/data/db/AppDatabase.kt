package com.example.practice.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practice.data.db.entity.FollowerEntity
import com.example.practice.data.db.entity.NewsEntity
import com.example.practice.data.db.entity.NewsFollowersCrossRef
import com.example.practice.model.Category

@Database(
    entities = [NewsEntity::class, FollowerEntity::class, Category::class, NewsFollowersCrossRef::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    abstract fun categoryDao(): CategoryDao
}

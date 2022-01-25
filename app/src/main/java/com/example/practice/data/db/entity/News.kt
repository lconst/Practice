package com.example.practice.data.db.entity

import androidx.room.Entity
import androidx.room.TypeConverters
import androidx.room.PrimaryKey
import com.example.practice.data.db.Converter

@Entity(tableName = "news")
@TypeConverters(Converter::class)
data class NewsEntity(
    @PrimaryKey(autoGenerate = false)
    val newsId: Int,
    val categoriesId: List<Int>,
    val dateEnd: Long,
    val dateStart: Long,
    val description: String,
    val email: String,
    val imagesResourcesNames: List<String>,
    val location: String,
    val owner: String,
    val phoneNumbers: List<String>,
    val posterResourceName: String,
    val site: String,
    val title: String,
    val isRead: Boolean
)

@Entity(tableName = "followers")
data class FollowerEntity(
    val avatarResourceName: String,
    @PrimaryKey(autoGenerate = false)
    val followerId: Int,
    val name: String,
    val newOwnerId: Int
)

@Entity(primaryKeys = ["newsId", "followerId"])
data class NewsFollowersCrossRef(
    val newsId: Int,
    val followerId: Int
)

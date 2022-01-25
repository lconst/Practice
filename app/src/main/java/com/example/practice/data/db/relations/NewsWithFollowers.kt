package com.example.practice.data.db.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.practice.data.db.entity.FollowerEntity
import com.example.practice.data.db.entity.NewsEntity
import com.example.practice.data.db.entity.NewsFollowersCrossRef

data class NewsWithFollowers(
    @Embedded val news: NewsEntity,
    @Relation(
        parentColumn = "newsId",
        entityColumn = "followerId",
        associateBy = Junction(NewsFollowersCrossRef::class)
    )
    val followerEntities: List<FollowerEntity>
)

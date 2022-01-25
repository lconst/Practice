package com.example.practice.data.db.mappers

import com.example.practice.data.db.entity.FollowerEntity
import com.example.practice.model.News

class NewsToFollowerMapper : Mapper<News, List<FollowerEntity>> {
    override fun mapFrom(source: News): List<FollowerEntity> {
        return source.followers.map { follower ->
            FollowerEntity(
                avatarResourceName = follower.avatarResourceName,
                followerId = follower.id,
                name = follower.name,
                newOwnerId = source.id
            )
        }
    }
}

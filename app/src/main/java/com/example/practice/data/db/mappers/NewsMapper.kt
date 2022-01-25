package com.example.practice.data.db.mappers

import com.example.practice.data.db.entity.NewsEntity
import com.example.practice.data.db.relations.NewsWithFollowers
import com.example.practice.model.Follower
import com.example.practice.model.News

class NewsToEntityMapper : Mapper<News, NewsEntity> {
    override fun mapFrom(source: News): NewsEntity {
        return NewsEntity(
            newsId = source.id,
            categoriesId = source.categoriesId,
            dateEnd = source.dateEnd,
            dateStart = source.dateStart,
            description = source.description,
            email = source.email,
            imagesResourcesNames = source.imagesResourcesNames,
            location = source.location,
            owner = source.owner,
            phoneNumbers = source.phoneNumbers,
            posterResourceName = source.posterResourceName,
            site = source.site,
            title = source.title,
            isRead = source.isRead
        )
    }
}

class NewsToModelMapper : Mapper<NewsWithFollowers, News> {
    override fun mapFrom(source: NewsWithFollowers): News {
        return News(
            id = source.news.newsId,
            categoriesId = source.news.categoriesId,
            dateEnd = source.news.dateEnd,
            dateStart = source.news.dateStart,
            description = source.news.description,
            email = source.news.email,
            imagesResourcesNames = source.news.imagesResourcesNames,
            location = source.news.location,
            owner = source.news.owner,
            phoneNumbers = source.news.phoneNumbers,
            posterResourceName = source.news.posterResourceName,
            site = source.news.site,
            title = source.news.title,
            isRead = source.news.isRead,
            followers = source.followerEntities.map { follower ->
                Follower(
                    follower.avatarResourceName,
                    follower.followerId,
                    follower.name
                )
            }
        )
    }
}

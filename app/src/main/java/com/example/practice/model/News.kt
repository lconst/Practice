package com.example.practice.model

import java.util.Date

data class News(
    val id: Int,
    val imageId: Int,
    val title: String,
    val description: String,
    val date: Date,
    val category: NewsCategory
)

enum class NewsCategory {
    CHILDREN, ADULT, ELDERLY, ANIMALS, EVENTS
}

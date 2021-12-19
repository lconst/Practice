package com.example.practice.presentation.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practice.data.CategoryRepository
import com.example.practice.data.NewsRepository
import com.example.practice.model.News

class NewsViewModel(
    private val newsRepository: NewsRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val newsListMutable = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>> get() = newsListMutable

    fun loadNews() {
        val filterCategoriesIds = categoryRepository.getCategories()
            .filter { category -> category.isEnabled }.map { category -> category.id }

        newsListMutable.value = newsRepository.getNewsAll()
            .filter { news ->
                checkNewsByFilter(news, filterCategoriesIds)
            }
    }

    private fun checkNewsByFilter(news: News, categoriesId: List<Int>): Boolean {
        var found = false
        news.categoriesId.forEach { id ->
            if (categoriesId.contains(id)) {
                found = true
            }
        }
        return found
    }
}

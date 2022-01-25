package com.example.practice.presentation.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practice.data.CategoryRepository
import com.example.practice.data.NewsRepository

@Suppress("UNCHECKED_CAST")
class NewViewModelFactory(
    private val repository: NewsRepository,
    private val categoryRepository: CategoryRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        NewsViewModel::class.java -> NewsViewModel(repository, categoryRepository)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}

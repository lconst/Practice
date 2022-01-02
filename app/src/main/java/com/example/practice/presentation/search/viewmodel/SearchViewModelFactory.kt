package com.example.practice.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practice.data.NewsRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory(private val repository: NewsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        SearchViewModel::class.java -> SearchViewModel(repository)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}

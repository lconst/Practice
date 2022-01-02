package com.example.practice.presentation.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practice.data.NewsRepository
import com.example.practice.model.News

class SearchViewModel(newsRepository: NewsRepository) : ViewModel() {

    private val newsListMutable = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>> get() = newsListMutable

    private val isDataLoadingMutable = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> get() = isDataLoadingMutable

    private val searchQueryMutable = MutableLiveData<String>()
    val searchQuery: LiveData<String> get() = searchQueryMutable

    init {
        isDataLoadingMutable.value = true
        newsRepository.getNewsAllObservable()
            .subscribe { news ->
                newsListMutable.value = news
                isDataLoadingMutable.value = false
            }
    }

    fun updateSearchQuery(query: String) {
        searchQueryMutable.postValue(query)
    }
}

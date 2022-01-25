package com.example.practice.presentation.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practice.data.NewsRepository
import com.example.practice.model.News
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class SearchViewModel(newsRepository: NewsRepository) : ViewModel() {

    private val newsListMutable = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>> get() = newsListMutable

    private val isDataLoadingMutable = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> get() = isDataLoadingMutable

    private val searchQueryMutable = MutableLiveData<String>()
    val searchQuery: LiveData<String> get() = searchQueryMutable

    private val compositeDisposable = CompositeDisposable()

    init {
        isDataLoadingMutable.value = true
        compositeDisposable.add(
            newsRepository.getNewsAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { news ->
                    newsListMutable.value = news
                    isDataLoadingMutable.value = false
                }
        )
    }

    fun updateSearchQuery(query: String) {
        searchQueryMutable.postValue(query)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

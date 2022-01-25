package com.example.practice.presentation.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practice.data.CategoryRepository
import com.example.practice.data.NewsRepository
import com.example.practice.model.Category
import com.example.practice.model.News
import com.example.practice.utils.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.serialization.ExperimentalSerializationApi
import timber.log.Timber
import java.util.concurrent.TimeUnit

@ExperimentalSerializationApi
class NewsViewModel(
    private val newsRepository: NewsRepository,
    private val categoryRepository: CategoryRepository,
) : ViewModel() {

    private val newsListMutable = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>> get() = newsListMutable

    private val isDataLoadingMutable = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> get() = isDataLoadingMutable

    private val compositeDisposable = CompositeDisposable()

    init {
        loadNews()
        observeChangeCategories()
    }

    private fun loadNews() {
        isDataLoadingMutable.value = true
        val disposable = newsRepository.getNewsAll()
            .flatMap { news ->
                newsListMutable.postValue(news)
                categoryRepository.getCategories()
            }
            .subscribeOn(Schedulers.io())
            .delay(SLEEP_TIME, TimeUnit.MILLISECONDS)
            .doOnSubscribe { Timber.d("loadNews doOnSubscribe ${Thread.currentThread()}") }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { Timber.d("loadNews doOnSuccess ${Thread.currentThread()}") }
            .subscribe { categories ->
                isDataLoadingMutable.value = false
                newsListMutable.value = newsListMutable.value?.let { getActualNews(categories, it) }
            }
        compositeDisposable.add(disposable)
    }

    private fun observeChangeCategories() {
        val disposable = categoryRepository.categoryChanged
            .subscribe {
                loadNews()
            }
        compositeDisposable.add(disposable)
    }

    private fun getActualNews(categories: List<Category>, news: List<News>): List<News> {
        val activeCategoryIds = getActualCategoryIds(categories)
        return getActualNewsForCategories(news, activeCategoryIds)
    }

    private fun getActualCategoryIds(categories: List<Category>): List<Int> {
        return categories.filter { category -> category.isEnabled }
            .map { category -> category.id }
    }

    private fun getActualNewsForCategories(news: List<News>, categoriesId: List<Int>): List<News> {
        return news.filter { item -> checkNewsByFilter(item, categoriesId) }
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

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

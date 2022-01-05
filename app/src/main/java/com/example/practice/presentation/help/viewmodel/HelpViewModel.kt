package com.example.practice.presentation.help.viewmodel

import androidx.lifecycle.*
import com.example.practice.data.CategoryRepository
import com.example.practice.model.Category
import com.example.practice.utils.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

class HelpViewModel(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

    private val categoriesListMutable = MutableLiveData<List<Category>>()
    val categoriesList: LiveData<List<Category>> get() = categoriesListMutable

    private val isDataLoadingMutable = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> get() = isDataLoadingMutable

    private val compositeDisposable = CompositeDisposable()

    init {
        loadCategories()
    }

    private fun loadCategories() {
        val disposable = categoryRepository.getCategories()
            .doOnSubscribe {
                isDataLoadingMutable.postValue(true)
                Timber.d("loadCategories doOnSubscribe ${Thread.currentThread().name}")
            }
            .subscribeOn(Schedulers.io())
            .delay(SLEEP_TIME, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { categories ->
                categoriesListMutable.value = categories
                isDataLoadingMutable.value = false
            }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

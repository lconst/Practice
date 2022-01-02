package com.example.practice.presentation.news.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.practice.concurrency.GetDataIntentService
import com.example.practice.data.CategoryRepository
import com.example.practice.data.NewsRepository
import com.example.practice.model.News
import com.example.practice.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.Executors

class NewsViewModel(
    private val newsRepository: NewsRepository,
    private val categoryRepository: CategoryRepository,
    private val context: Application
) : AndroidViewModel(context) {

    private val newsListMutable = MutableLiveData<List<News>>()
    val newsList: LiveData<List<News>> get() = newsListMutable

    private val isDataLoadingMutable = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> get() = isDataLoadingMutable

    private var dataReceiver: DataBroadcastReceiver? = null

    init {
        when (CONCURRENT_MODE) {
            CONCURRENT_MODE_COROUTINES -> loadNews()
            CONCURRENT_MODE_ASYNC_TASK -> GetDataAsyncTask().execute()
            CONCURRENT_MODE_EXECUTOR -> loadCategoryByExecutor()
            CONCURRENT_MODE_INTENT_SERVICE -> {
                registerReceiver()
                loadCategoriesByIntentService()
            }
        }
    }

    private fun loadNews() {
        isDataLoadingMutable.value = true
        viewModelScope.launch {
            delay(SLEEP_TIME)
            val filterCategoriesIds = categoryRepository.getCategoriesSuspend()
                .filter { category -> category.isEnabled }
                .map { category -> category.id }

            newsListMutable.value = newsRepository.getNewsAllSuspend()
                .filter { news ->
                    checkNewsByFilter(news, filterCategoriesIds)
                }
            isDataLoadingMutable.value = false
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

    private fun getFilteredNews(): List<News> {
        val filterCategoriesIds = categoryRepository.getCategories()
            .filter { category -> category.isEnabled }
            .map { category -> category.id }

        return newsRepository.getNewsAll()
            .filter { news ->
                checkNewsByFilter(news, filterCategoriesIds)
            }
    }

    @SuppressLint("StaticFieldLeak")
    inner class GetDataAsyncTask :
        AsyncTask<Unit, Unit, List<News>>() {

        override fun onPreExecute() {
            super.onPreExecute()
            isDataLoadingMutable.value = true
            Timber.d("GetDataAsyncTask start")
        }

        override fun doInBackground(vararg params: Unit): List<News> {
            try {
                Thread.sleep(SLEEP_TIME)
                return getFilteredNews()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return arrayListOf()
        }

        override fun onPostExecute(result: List<News>) {
            super.onPostExecute(result)
            isDataLoadingMutable.value = false
            newsListMutable.value = result
            Timber.d("GetDataAsyncTask end")
        }
    }

    private fun loadCategoryByExecutor() {
        isDataLoadingMutable.value = true
        Executors.newSingleThreadExecutor()
            .execute {
                try {
                    Thread.sleep(SLEEP_TIME)
                    newsListMutable.postValue(getFilteredNews())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    isDataLoadingMutable.postValue(false)
                }
            }
    }

    private fun loadCategoriesByIntentService() {
        isDataLoadingMutable.value = true
        val intentService = Intent(context, GetDataIntentService::class.java)
        intentService.putExtra(
            GetDataIntentService.DATA_TYPE_KEY,
            GetDataIntentService.DATA_TYPE_NEWS
        )
        context.startService(intentService)
    }

    private fun registerReceiver() {
        dataReceiver = DataBroadcastReceiver()
        val intentFilter = IntentFilter(GetDataIntentService.ACTION_GET_DATA)
            .apply { addCategory(Intent.CATEGORY_DEFAULT) }
        context.registerReceiver(dataReceiver, intentFilter)
    }

    inner class DataBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val dataType = intent?.getIntExtra(GetDataIntentService.DATA_TYPE_KEY, 0)
            if (dataType == GetDataIntentService.DATA_TYPE_NEWS) {
                newsListMutable.value =
                    intent.getParcelableArrayListExtra(GetDataIntentService.RESPONSE_GET_DATA_KEY)
            }
            isDataLoadingMutable.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        dataReceiver?.let { context.unregisterReceiver(dataReceiver) }
    }
}

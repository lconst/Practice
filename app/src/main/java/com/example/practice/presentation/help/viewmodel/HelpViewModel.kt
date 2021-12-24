package com.example.practice.presentation.help.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.practice.concurrency.GetDataIntentService
import com.example.practice.data.CategoryRepository
import com.example.practice.model.Category
import com.example.practice.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.Executors

class HelpViewModel(
    private val categoryRepository: CategoryRepository,
    private val context: Application
) : AndroidViewModel(context) {

    private val categoriesListMutable = MutableLiveData<List<Category>>()
    val categoriesList: LiveData<List<Category>> get() = categoriesListMutable

    private val isDataLoadingMutable = MutableLiveData<Boolean>()
    val isDataLoading: LiveData<Boolean> get() = isDataLoadingMutable

    private var dataReceiver: DataBroadcastReceiver? = null

    init {
        when (CONCURRENT_MODE) {
            CONCURRENT_MODE_COROUTINES -> {
                loadCategories()
            }
            CONCURRENT_MODE_ASYNC_TASK -> {
                GetDataAsyncTask().execute()
            }
            CONCURRENT_MODE_EXECUTOR -> {
                loadCategoryByExecutor()
            }
            CONCURRENT_MODE_INTENT_SERVICE -> {
                registerReceiver()
                loadCategoriesByIntentService()
            }
        }
    }

    private fun loadCategories() {
        isDataLoadingMutable.value = true
        viewModelScope.launch {
            delay(SLEEP_TIME)
            categoriesListMutable.value = categoryRepository.getCategoriesSuspend()
            isDataLoadingMutable.value = false
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class GetDataAsyncTask :
        AsyncTask<Unit, Unit, List<Category>>() {

        override fun onPreExecute() {
            super.onPreExecute()
            isDataLoadingMutable.value = true
            Timber.d("GetDataAsyncTask start")
        }

        override fun doInBackground(vararg params: Unit): List<Category> {
            try {
                Thread.sleep(SLEEP_TIME)
                return categoryRepository.getCategories()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return arrayListOf()
        }

        override fun onPostExecute(result: List<Category>) {
            super.onPostExecute(result)
            isDataLoadingMutable.value = false
            categoriesListMutable.value = result
            Timber.d("GetDataAsyncTask end")
        }
    }

    private fun loadCategoryByExecutor() {
        isDataLoadingMutable.value = true
        Executors.newSingleThreadExecutor()
            .execute {
                try {
                    Thread.sleep(SLEEP_TIME)
                    categoriesListMutable.postValue(categoryRepository.getCategories())
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
            GetDataIntentService.DATA_TYPE_CATEGORIES
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
            if (dataType == GetDataIntentService.DATA_TYPE_CATEGORIES) {
                categoriesListMutable.value =
                    intent.getParcelableArrayListExtra(GetDataIntentService.RESPONSE_GET_DATA_KEY)
            }
            isDataLoadingMutable.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        dataReceiver?.let { context.unregisterReceiver(it) }
    }
}

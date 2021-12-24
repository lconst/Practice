package com.example.practice.presentation.help.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practice.data.CategoryRepository
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class HelpViewModelFactory(
    private val categoryRepository: CategoryRepository,
    private val context: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        HelpViewModel::class.java -> HelpViewModel(categoryRepository, context)
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}

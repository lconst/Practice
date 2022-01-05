package com.example.practice.presentation.news.filter

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.practice.PracticeApp
import com.example.practice.PracticeApp.Companion.APP_PREFERENCES
import com.example.practice.R
import com.example.practice.databinding.FragmentFilterCategoryBinding
import com.example.practice.model.Category
import com.example.practice.presentation.MainActivity
import com.example.practice.utils.setupToolbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class FilterFragment : Fragment(R.layout.fragment_filter_category) {

    private val binding by viewBinding(FragmentFilterCategoryBinding::bind)
    private var categoryList: List<Category> = listOf()
    private val adapter by lazy { FilterCategoryAdapter() }
    private val repository = PracticeApp.instance.categoryRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initRecycler()
        loadCategories()
    }

    private fun initToolbar() {
        val activity = (requireActivity() as MainActivity)
        with(activity) {
            setupToolbar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
        setHasOptionsMenu(true)
    }

    private fun initRecycler() {
        binding.recycler.adapter = adapter
    }

    private fun loadCategories() {
        repository.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { categories ->
                adapter.submitList(categories)
                categoryList = categories
            }
    }

    private fun saveFilterCategories() {
        val sharedPref =
            requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            categoryList.forEach { category ->
                putBoolean(category.name + "id ${category.id}", category.isEnabled)
            }
            apply()
        }
        repository.update(categoryList)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> findNavController().navigateUp()
            R.id.done -> {
                saveFilterCategories()
                findNavController().navigateUp()
            }
        }
        return true
    }
}

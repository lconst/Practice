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

class FilterFragment : Fragment(R.layout.fragment_filter_category) {

    private val binding by viewBinding(FragmentFilterCategoryBinding::bind)
    private val categoryList by lazy { loadCategories() }
    private val adapter by lazy { FilterCategoryAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initRecycler()
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
        adapter.submitList(categoryList)
    }

    private fun loadCategories(): List<Category> {
        return PracticeApp.instance.categoryRepository.getCategories()
    }

    private fun saveFilterCategories() {
        val sharedPref = requireActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            categoryList.forEach { category ->
                putBoolean(category.name + "id ${category.id}", category.isEnabled)
            }
            apply()
        }
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

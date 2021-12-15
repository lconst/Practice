package com.example.practice.presentation.news.filter

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.practice.R
import com.example.practice.databinding.FragmentFilterBinding
import com.example.practice.model.Filter
import com.example.practice.presentation.MainActivity
import com.example.practice.utils.setupToolbar

class FilterFragment : Fragment(R.layout.fragment_filter) {
    private val binding by viewBinding(FragmentFilterBinding::bind)

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
        binding.recycler.adapter = FilterAdapter(createFakeFilters())
    }

    private fun createFakeFilters() = listOf(
        Filter(getString(R.string.children), true),
        Filter(getString(R.string.adult), true),
        Filter(getString(R.string.elderly), true),
        Filter(getString(R.string.animals), true),
        Filter(getString(R.string.events), true)
    )

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> findNavController().navigateUp()
            R.id.done -> findNavController().navigateUp()
        }
        return true
    }
}

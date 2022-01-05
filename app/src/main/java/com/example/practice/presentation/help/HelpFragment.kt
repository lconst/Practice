package com.example.practice.presentation.help

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.practice.PracticeApp
import com.example.practice.R
import com.example.practice.databinding.FragmentHelpBinding
import com.example.practice.presentation.help.viewmodel.HelpViewModel
import com.example.practice.presentation.help.viewmodel.HelpViewModelFactory

class HelpFragment : Fragment(R.layout.fragment_help) {

    private val binding by viewBinding(FragmentHelpBinding::bind)
    private val adapter = HelpCategoryAdapter()
    private val viewModel: HelpViewModel by viewModels {
        HelpViewModelFactory(
            PracticeApp.instance.categoryRepository
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        observeCategoriesByViewModel()
    }

    private fun initRecycler() {
        binding.recycler.adapter = adapter
        binding.recycler.addItemDecoration(
            SpacesItemDecoration(
                SPAN_COUNT,
                resources.getDimensionPixelSize(R.dimen.margin_05x),
                true
            )
        )
    }

    private fun observeCategoriesByViewModel() {
        viewModel.categoriesList.observe(viewLifecycleOwner, { categories ->
            adapter.submitList(categories)
        })
        viewModel.isDataLoading.observe(viewLifecycleOwner, { isLoading ->
            binding.progressBar.isVisible = isLoading
        })
    }

    companion object {
        private const val SPAN_COUNT = 2
    }
}

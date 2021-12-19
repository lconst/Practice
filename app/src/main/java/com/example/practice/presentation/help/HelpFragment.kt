package com.example.practice.presentation.help

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.practice.PracticeApp
import com.example.practice.R
import com.example.practice.databinding.FragmentHelpBinding
import com.example.practice.model.Category

class HelpFragment : Fragment(R.layout.fragment_help) {

    private val binding by viewBinding(FragmentHelpBinding::bind)
    private val categoryList by lazy { loadCategories() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        binding.recycler.adapter = HelpCategoryAdapter(categoryList)
        binding.recycler.addItemDecoration(
            SpacesItemDecoration(
                SPAN_COUNT,
                resources.getDimensionPixelSize(R.dimen.margin_05x),
                true
            )
        )
    }

    private fun loadCategories(): List<Category> {
        return PracticeApp.instance.categoryRepository.getCategories()
    }

    companion object {
        private const val SPAN_COUNT = 2
    }
}

package com.example.practice.presentation.help

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.practice.R
import com.example.practice.databinding.FragmentHelpBinding
import com.example.practice.model.HelpCategory

class HelpFragment : Fragment(R.layout.fragment_help) {

    private val binding by viewBinding(FragmentHelpBinding::bind)
    private val categoryList = mutableListOf<HelpCategory>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        generateFakeCategories()
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

    private fun generateFakeCategories() {
        repeat(5) {
            categoryList.add(HelpCategory(R.drawable.ic_profile, "Lorem"))
        }
    }

    companion object {
        private const val SPAN_COUNT = 2
    }
}

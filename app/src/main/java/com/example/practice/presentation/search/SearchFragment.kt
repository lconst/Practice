package com.example.practice.presentation.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.practice.R
import com.example.practice.databinding.FragmentSearchBinding
import com.google.android.material.tabs.TabLayoutMediator

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val tabsTitles: Array<String> by lazy { requireContext().resources.getStringArray(R.array.tabs_title) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearch()
        initTabs()
    }

    private fun initSearch() {
        with(binding) {
            search.maxWidth = Int.MAX_VALUE
            search.setOnSearchClickListener { title.visibility = View.GONE }
            search.setOnCloseListener {
                title.isVisible = true
                false
            }
        }
    }

    private fun initTabs() {
        with(binding) {
            pager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
            TabLayoutMediator(tabLayout, pager) { tab, position ->
                tab.text = tabsTitles[position]
            }.attach()
        }
    }
}

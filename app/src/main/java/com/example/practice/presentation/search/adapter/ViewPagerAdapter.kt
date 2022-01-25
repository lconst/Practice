package com.example.practice.presentation.search.adapter

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.practice.model.Charity
import com.example.practice.model.News
import com.example.practice.presentation.search.SearchPageFragment

class ViewPagerAdapter(
    private val newsList: List<News>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private var eventsList: List<Charity> = emptyList()
    private var nkoList: List<Charity> = emptyList()

    init {
        sortNewsByGroup()
    }

    override fun getItemCount() = TABS

    override fun createFragment(position: Int): Fragment {
        var arguments = Bundle()
        when (position) {
            0 -> arguments = bundleOf(SearchPageFragment.DATA_KEY to eventsList)
            1 -> arguments = bundleOf(SearchPageFragment.DATA_KEY to nkoList)
        }
        return SearchPageFragment().also { it.arguments = arguments }
    }

    private fun sortNewsByGroup() {
        eventsList = newsList.map { item -> Charity(item.title) }
        nkoList = newsList.map { item -> Charity(item.owner) }
    }

    companion object {
        private const val TABS = 2
    }
}

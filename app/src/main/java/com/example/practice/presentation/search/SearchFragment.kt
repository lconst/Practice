package com.example.practice.presentation.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.practice.PracticeApp
import com.example.practice.R
import com.example.practice.databinding.FragmentSearchBinding
import com.example.practice.model.News
import com.example.practice.presentation.search.adapter.ViewPagerAdapter
import com.example.practice.presentation.search.viewmodel.SearchViewModel
import com.example.practice.presentation.search.viewmodel.SearchViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val tabsTitles: Array<String> by lazy { requireContext().resources.getStringArray(R.array.tabs_title) }
    private val viewModel: SearchViewModel by viewModels { SearchViewModelFactory(PracticeApp.instance.newsRepository) }
    private var newsList: List<News> = emptyList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearch()
        viewModel.isDataLoading.observe(viewLifecycleOwner, { isLoading ->
            binding.progressBar.isVisible = isLoading
        })
        viewModel.newsList.observe(viewLifecycleOwner, { newsList ->
            this.newsList = newsList
            initTabs(newsList)
        })
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
        handleQuerySearch()
    }

    private fun handleQuerySearch() {
        RxSearchObservable().fromView(binding.search)
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe { text -> viewModel.updateSearchQuery(text) }
    }

    private fun initTabs(newsList: List<News>) {
        with(binding) {
            pager.adapter = ViewPagerAdapter(newsList, childFragmentManager, lifecycle)
            TabLayoutMediator(tabLayout, pager) { tab, position ->
                tab.text = tabsTitles[position]
            }.attach()
        }
    }
}

package com.example.practice.presentation.search

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.practice.R
import com.example.practice.databinding.FragmentSearchPageBinding
import com.example.practice.model.Charity
import com.example.practice.presentation.search.adapter.CharityAdapter
import com.example.practice.presentation.search.viewmodel.SearchViewModel
import timber.log.Timber

@Suppress("UNCHECKED_CAST")
class SearchPageFragment : Fragment(R.layout.fragment_search_page) {
    private val binding by viewBinding(FragmentSearchPageBinding::bind)
    private val charityList by lazy { arguments?.get(DATA_KEY) as List<Charity> }
    private val adapter by lazy { CharityAdapter() }
    private val viewModel: SearchViewModel by viewModels({ requireParentFragment() })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        Timber.d(" SearchPageFragment OnViewCreated")
        handleSearch()
    }

    override fun onResume() {
        super.onResume()
        handleSearch()
    }

    override fun onStop() {
        super.onStop()
        viewModel.searchQuery.removeObservers(viewLifecycleOwner)
    }

    private fun handleSearch() {
        viewModel.searchQuery.observe(viewLifecycleOwner, { text ->
            if (text.isEmpty()) {
                showPlug(true)
            } else {
                showPlug(false)
                val newCharityList = charityList.filter { item ->
                    item.name.contains(text, true)
                }
                adapter.submitList(newCharityList)
                binding.keyWords.text = resources.getString(R.string.search_key_words, text)
                binding.resultNumber.text =
                    resources.getString(R.string.search_result_number, newCharityList.size)
            }
        })
    }

    private fun initRecycler() {
        binding.recycler.adapter = adapter
    }

    private fun showPlug(show: Boolean) {
        Timber.d("showPlug $show")
        binding.emptySearchPlug.root.isVisible = show
    }

    companion object {
        const val DATA_KEY = "data_key"
    }
}

package com.example.practice.presentation.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.practice.R
import com.example.practice.databinding.FragmentSearchPageBinding
import com.example.practice.model.Charity

class SearchPageFragment : Fragment(R.layout.fragment_search_page) {
    private val binding by viewBinding(FragmentSearchPageBinding::bind)
    private val charityList by lazy { arguments?.get(DATA_KEY) as List<Charity> }
    private val adapter by lazy { CharityAdapter(charityList) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    override fun onResume() {
        super.onResume()
        val numCharity = (0..10).random()
        adapter.differ.submitList(getRandomSomeCharities(charityList, numCharity))
    }

    private fun getRandomSomeCharities(charities: List<Charity>, n: Int): List<Charity> {
        return charities.asSequence().shuffled().take(n).toList()
    }

    private fun initRecycler() {
        binding.recycler.adapter = adapter
    }

    companion object {
        const val DATA_KEY = "data_key"
    }
}

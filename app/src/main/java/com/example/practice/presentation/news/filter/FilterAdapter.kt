package com.example.practice.presentation.news.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.practice.BaseRecyclerAdapter
import com.example.practice.BaseViewHolder
import com.example.practice.databinding.FilterItemBinding
import com.example.practice.model.Filter

class FilterAdapter(filters: List<Filter>) : BaseRecyclerAdapter<Filter>(filters) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Filter> {
        val binding = FilterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilterViewHolder(binding)
    }
}

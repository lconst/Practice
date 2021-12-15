package com.example.practice.presentation.news.filter

import com.example.practice.BaseViewHolder
import com.example.practice.databinding.FilterItemBinding
import com.example.practice.model.Filter

class FilterViewHolder(private val binding: FilterItemBinding) :
    BaseViewHolder<Filter>(binding.root) {
    override fun bind(entity: Filter) {
        with(binding) {
            category.text = entity.name
            category.isEnabled = entity.isEnabled
        }
    }
}

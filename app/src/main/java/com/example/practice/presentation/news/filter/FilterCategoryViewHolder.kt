package com.example.practice.presentation.news.filter

import com.example.practice.BaseViewHolder
import com.example.practice.databinding.FilterCategoryItemBinding
import com.example.practice.model.Category

class FilterCategoryViewHolder(private val binding: FilterCategoryItemBinding) :
    BaseViewHolder<Category>(binding.root) {
    override fun bind(entity: Category) {
        with(binding) {
            category.text = entity.name
            category.isChecked = entity.isEnabled
            category.setOnCheckedChangeListener { _, isChecked ->
                entity.isEnabled = isChecked
            }
        }
    }
}

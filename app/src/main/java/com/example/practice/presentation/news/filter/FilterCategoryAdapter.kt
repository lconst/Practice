package com.example.practice.presentation.news.filter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.practice.databinding.FilterCategoryItemBinding
import com.example.practice.model.Category

class FilterCategoryAdapter : ListAdapter<Category, FilterCategoryViewHolder>(differCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterCategoryViewHolder {
        val binding = FilterCategoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FilterCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilterCategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private val differCallback = object : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}

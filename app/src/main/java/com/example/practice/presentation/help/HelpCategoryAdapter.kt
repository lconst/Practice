package com.example.practice.presentation.help

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.practice.databinding.HelpCategoryItemBinding
import com.example.practice.model.Category

class HelpCategoryAdapter :
    ListAdapter<Category, HelpCategoryViewHolder>(differCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpCategoryViewHolder {
        val binding =
            HelpCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HelpCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HelpCategoryViewHolder, position: Int) {
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

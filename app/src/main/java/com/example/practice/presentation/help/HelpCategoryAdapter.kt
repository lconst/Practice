package com.example.practice.presentation.help

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.practice.BaseRecyclerAdapter
import com.example.practice.databinding.HelpCategoryItemBinding
import com.example.practice.model.Category

class HelpCategoryAdapter(categories: List<Category>) :
    BaseRecyclerAdapter<Category>(categories) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpCategoryViewHolder {
        val binding =
            HelpCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HelpCategoryViewHolder(binding)
    }
}

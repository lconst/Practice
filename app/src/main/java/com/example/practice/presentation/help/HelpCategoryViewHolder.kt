package com.example.practice.presentation.help

import androidx.recyclerview.widget.RecyclerView
import com.example.practice.databinding.HelpCategoryItemBinding
import com.example.practice.model.HelpCategory

class HelpCategoryViewHolder(private val itemBinding: HelpCategoryItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(category: HelpCategory) {
        with(itemBinding) {
            categoryImage.setImageResource(category.image)
            categoryName.text = category.name
        }
    }
}

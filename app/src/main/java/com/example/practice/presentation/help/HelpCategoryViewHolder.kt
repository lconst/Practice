package com.example.practice.presentation.help

import com.example.practice.BaseViewHolder
import com.example.practice.databinding.HelpCategoryItemBinding
import com.example.practice.model.Category
import com.example.practice.utils.setImageByResourceName

class HelpCategoryViewHolder(private val itemBinding: HelpCategoryItemBinding) :
    BaseViewHolder<Category>(itemBinding.root) {

    override fun bind(entity: Category) {
        with(itemBinding) {
            categoryImage.setImageByResourceName(entity.imageResourceName)
            categoryName.text = entity.name
        }
    }
}

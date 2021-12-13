package com.example.practice.presentation.help

import com.example.practice.BaseViewHolder
import com.example.practice.databinding.HelpCategoryItemBinding
import com.example.practice.model.HelpCategory

class HelpCategoryViewHolder(private val itemBinding: HelpCategoryItemBinding) :
    BaseViewHolder<HelpCategory>(itemBinding.root) {

    override fun bind(entity: HelpCategory) {
        with(itemBinding) {
            categoryImage.setImageResource(entity.imageResourceId)
            categoryName.text = this.root.context.getText(entity.nameResourceId)
        }
    }
}

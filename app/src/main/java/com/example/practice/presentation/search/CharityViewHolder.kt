package com.example.practice.presentation.search

import com.example.practice.BaseViewHolder
import com.example.practice.databinding.CharityItemBinding
import com.example.practice.model.Charity

class CharityViewHolder(private val itemBinding: CharityItemBinding) : BaseViewHolder<Charity>(itemBinding.root) {
    override fun bind(entity: Charity) {
        itemBinding.name.text = entity.name
    }
}

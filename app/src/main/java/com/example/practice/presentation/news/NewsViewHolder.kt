package com.example.practice.presentation.news

import com.example.practice.BaseViewHolder
import com.example.practice.databinding.NewsItemBinding
import com.example.practice.model.News
import com.example.practice.utils.setImageByResourceName

class NewsViewHolder(
    private val binding: NewsItemBinding,
    private val clickListener: (Int) -> Unit
) : BaseViewHolder<News>(binding.root) {

    override fun bind(entity: News) {
        with(binding) {
            image.setImageByResourceName(entity.posterResourceName)
            title.text = entity.title
            description.text = entity.description
            date.text = entity.getDateFormatted()
        }
        binding.root.setOnClickListener { clickListener(entity.id) }
    }
}

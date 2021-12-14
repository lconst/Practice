package com.example.practice.presentation.news

import com.example.practice.BaseViewHolder
import com.example.practice.databinding.NewsItemBinding
import com.example.practice.model.News

class NewsViewHolder(private val binding: NewsItemBinding) : BaseViewHolder<News>(binding.root) {
    override fun bind(entity: News) {
        with(binding) {
            image.setImageResource(entity.imageId)
            title.text = entity.title
            description.text = entity.description
        }
    }
}

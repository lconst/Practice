package com.example.practice.presentation.news

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.practice.BaseRecyclerAdapter
import com.example.practice.BaseViewHolder
import com.example.practice.databinding.NewsItemBinding
import com.example.practice.model.News

class NewsAdapter(news: List<News>) : BaseRecyclerAdapter<News>(news) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<News> {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }
}

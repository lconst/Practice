package com.example.practice.presentation.news.details

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.practice.BaseRecyclerAdapter
import com.example.practice.BaseViewHolder
import com.example.practice.databinding.FollowerItemBinding
import com.example.practice.model.Follower

class NewsDetailsFollowersAdapter(followers: List<Follower>) :
    BaseRecyclerAdapter<Follower>(followers) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Follower> {
        val binding =
            FollowerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsDetailsFollowersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return VISIBLE_FOLLOWERS_NUMBER
    }

    companion object {
        const val VISIBLE_FOLLOWERS_NUMBER = 5
    }
}

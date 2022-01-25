package com.example.practice.presentation.news.details

import com.example.practice.BaseViewHolder
import com.example.practice.databinding.FollowerItemBinding
import com.example.practice.model.Follower
import com.example.practice.utils.setImageByResourceName

class NewsDetailsFollowersViewHolder(private val binding: FollowerItemBinding) :
    BaseViewHolder<Follower>(binding.root) {
    override fun bind(entity: Follower) {
        binding.image.setImageByResourceName(entity.avatarResourceName)
    }
}

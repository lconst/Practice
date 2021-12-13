package com.example.practice.presentation.profile.view

import com.example.practice.BaseViewHolder
import com.example.practice.databinding.FriendItemBinding
import com.example.practice.model.Friend

class FriendViewHolder(private val itemBinding: FriendItemBinding) : BaseViewHolder<Friend>(itemBinding.root) {

    override fun bind(entity: Friend) {
        with(itemBinding) {
            photo.setImageResource(entity.photo)
            fullName.text = entity.fullName
        }
    }
}

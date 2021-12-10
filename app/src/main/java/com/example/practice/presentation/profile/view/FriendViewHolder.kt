package com.example.practice.presentation.profile.view

import androidx.recyclerview.widget.RecyclerView
import com.example.practice.databinding.FriendItemBinding
import com.example.practice.model.Friend

class FriendViewHolder(private val itemBinding: FriendItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(friend: Friend) {
        with(itemBinding) {
            photo.setImageResource(friend.photo)
            fullName.text = friend.fullName
        }
    }
}

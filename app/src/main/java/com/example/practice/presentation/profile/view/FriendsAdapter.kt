package com.example.practice.presentation.profile.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.practice.BaseRecyclerAdapter
import com.example.practice.databinding.FriendItemBinding
import com.example.practice.model.Friend

class FriendsAdapter(friends: List<Friend>) : BaseRecyclerAdapter<Friend>(friends) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val binding = FriendItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FriendViewHolder(binding)
    }
}

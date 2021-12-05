package com.example.practice.presentation.profile.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.practice.databinding.FriendItemBinding
import com.example.practice.model.Friend

class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding by viewBinding(FriendItemBinding::bind)

    fun bind(friend: Friend) {
        with(binding) {
            photo.setImageResource(friend.photo)
            fullName.text = friend.fullName
        }
    }
}

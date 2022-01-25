package com.example.practice.presentation.news.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.practice.databinding.FollowerItemBinding
import com.example.practice.model.Follower
import timber.log.Timber

class NewsDetailsFollowersAdapter :
    ListAdapter<Follower, NewsDetailsFollowersViewHolder>(differCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsDetailsFollowersViewHolder {
        val binding =
            FollowerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsDetailsFollowersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        Timber.d("Current follower size ${currentList.size}")
        return if (currentList.size >= VISIBLE_FOLLOWERS_NUMBER) VISIBLE_FOLLOWERS_NUMBER
        else super.getItemCount()
    }

    override fun onBindViewHolder(holder: NewsDetailsFollowersViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        const val VISIBLE_FOLLOWERS_NUMBER = 5
    }
}

private val differCallback = object : DiffUtil.ItemCallback<Follower>() {
    override fun areItemsTheSame(oldItem: Follower, newItem: Follower): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Follower, newItem: Follower): Boolean {
        return oldItem == newItem
    }
}

package com.example.practice.presentation.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.practice.databinding.CharityItemBinding
import com.example.practice.model.Charity
import com.example.practice.presentation.search.CharityViewHolder

class CharityAdapter : ListAdapter<Charity, CharityViewHolder>(differCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharityViewHolder {
        val binding = CharityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

private val differCallback = object : DiffUtil.ItemCallback<Charity>() {
    override fun areItemsTheSame(oldItem: Charity, newItem: Charity): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Charity, newItem: Charity): Boolean {
        return oldItem == newItem
    }
}

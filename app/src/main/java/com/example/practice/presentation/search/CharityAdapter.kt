package com.example.practice.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.practice.BaseRecyclerAdapter
import com.example.practice.BaseViewHolder
import com.example.practice.databinding.CharityItemBinding
import com.example.practice.model.Charity

class CharityAdapter(charityList: List<Charity>) : BaseRecyclerAdapter<Charity>(charityList) {

    private val differCallback = object : DiffUtil.ItemCallback<Charity>() {
        override fun areItemsTheSame(oldItem: Charity, newItem: Charity): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Charity, newItem: Charity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Charity>, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Charity> {
        val binding = CharityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharityViewHolder(binding)
    }
}

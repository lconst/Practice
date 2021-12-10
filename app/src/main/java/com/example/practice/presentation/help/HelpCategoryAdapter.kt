package com.example.practice.presentation.help

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.databinding.HelpCategoryItemBinding
import com.example.practice.model.HelpCategory

class HelpCategoryAdapter(private val categories: List<HelpCategory>) : RecyclerView.Adapter<HelpCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpCategoryViewHolder {
        val binding = HelpCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HelpCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HelpCategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size
}

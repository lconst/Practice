package com.example.practice

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T>(private val dataList: List<T>) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size
}

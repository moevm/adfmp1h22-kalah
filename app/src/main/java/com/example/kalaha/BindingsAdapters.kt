package com.example.kalaha

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<GameStatistic>?) {
    val adapter = recyclerView.adapter as StatisticListAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0)
    }
}
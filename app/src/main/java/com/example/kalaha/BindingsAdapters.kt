package com.example.kalaha

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kalaha.R

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<GameStatistic>?) {
    val adapter = recyclerView.adapter as StatisticListAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0)
    }
}

@BindingAdapter("srcData")
fun bindSourceData(imageView: ImageView, data: Game.State) {
    if (data == Game.State.FirstPlayerTurn)
        imageView.setImageResource(R.drawable.ic_baseline_arrow_downward_24)
    if (data == Game.State.SecondPlayerTurn)
        imageView.setImageResource(R.drawable.ic_baseline_arrow_upward_24)
    if (data == Game.State.EndOfGame)
        imageView.setImageResource(R.drawable.ic_baseline_cancel_24)
}
/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.example.kalaha

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kalaha.databinding.StatisticsItemBinding

class StatisticListAdapter: ListAdapter<GameStatistic, StatisticListAdapter.ListViewHolder>(DiffCallback){
    companion object DiffCallback : DiffUtil.ItemCallback<GameStatistic>() {
        override fun areItemsTheSame(oldItem: GameStatistic, newItem: GameStatistic): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GameStatistic, newItem: GameStatistic): Boolean {
            return oldItem == newItem
        }
    }

    class ListViewHolder(private var binding: StatisticsItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: GameStatistic) {
            binding.game = item

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = StatisticsItemBinding.inflate(layoutInflater, parent, false)
                return ListViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


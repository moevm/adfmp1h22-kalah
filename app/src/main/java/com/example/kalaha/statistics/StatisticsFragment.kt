package com.example.kalaha.statistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.kalaha.StatisticListAdapter
import com.example.kalaha.statistics.StatisticsViewModel
import com.example.kalaha.databinding.FragmentStatisticsBinding


class StatisticsFragment : Fragment() {

    private lateinit var binding: FragmentStatisticsBinding
    private val viewModel: StatisticsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatisticsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.listOfStats.adapter = StatisticListAdapter()

        return binding.root
    }
}
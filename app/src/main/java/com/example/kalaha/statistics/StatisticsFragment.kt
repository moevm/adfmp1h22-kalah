package com.example.kalaha.statistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kalaha.MyViewModelFactory
import com.example.kalaha.StatisticListAdapter
import com.example.kalaha.database.GameStatisticDatabase
import com.example.kalaha.statistics.StatisticsViewModel
import com.example.kalaha.databinding.FragmentStatisticsBinding


class StatisticsFragment : Fragment() {

    private lateinit var binding: FragmentStatisticsBinding
    private lateinit var viewModel: StatisticsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatisticsBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dataSource = GameStatisticDatabase.getInstance(application).gameStatisticDatabaseDao
        val viewModelFactory = MyViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory)[StatisticsViewModel::class.java]

        binding.viewModel = viewModel
        binding.listOfStats.adapter = StatisticListAdapter()

        binding.home.setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }

        return binding.root
    }
}
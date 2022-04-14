package com.example.kalaha.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kalaha.R

import com.example.kalaha.databinding.FragmentMenuBinding
import java.util.*


class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private val viewModel: MenuViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        viewModel.navigateToSingleGame.observe(viewLifecycleOwner) { navigate ->
            if (navigate) {
                val navController = findNavController()
                navController.navigate(R.id.action_menuFragment_to_singleGameFragment)
                viewModel.onNavigatedToSingleGame()
            }
        }


        viewModel.navigateToMultiGame.observe(viewLifecycleOwner) { navigate ->
            if (navigate) {
                val navController = findNavController()
                navController.navigate(R.id.action_menuFragment_to_multiGameFragment)
                viewModel.onNavigatedToMultiGame()
            }
        }

        viewModel.navigateToOptions.observe(viewLifecycleOwner) { navigate ->
            if (navigate) {
                val navController = findNavController()
                navController.navigate(R.id.action_menuFragment_to_optionsFragment)
                viewModel.onNavigatedToOptions()
            }
        }

        viewModel.navigateToStatistics.observe(viewLifecycleOwner) { navigate ->
            if (navigate) {
                val navController = findNavController()
                navController.navigate(R.id.action_menuFragment_to_statisticsFragment)
                viewModel.onNavigatedToStatistics()
            }
        }


        return binding.root
    }
}
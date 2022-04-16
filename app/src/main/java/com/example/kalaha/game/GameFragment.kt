package com.example.kalaha.game

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kalaha.Game
import com.example.kalaha.R
import com.example.kalaha.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        var isChronometerRunning = false;


        // Запуск и пеерзапуск таймера
        viewModel.state.observe(viewLifecycleOwner) {
            if(it == Game.State.EndOfGame){
                binding.time.text = binding.simpleChronometer.text
                binding.simpleChronometer.base = SystemClock.elapsedRealtime()
                binding.simpleChronometer.stop()
                isChronometerRunning = false
            }
            if(!isChronometerRunning && it != Game.State.EndOfGame){
                binding.simpleChronometer.start()
                isChronometerRunning = true
            }
        }

        binding.homeButton.setOnClickListener{
            TODO("save to DB")
            val navController = findNavController()
            navController.navigateUp()
        }

        binding.home.setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }

        binding.restartButton.setOnClickListener{
            TODO("save to DB")
            viewModel.onRestart()
        }

        return binding.root
    }
}
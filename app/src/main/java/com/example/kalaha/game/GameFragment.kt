package com.example.kalaha.game

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kalaha.Game
import com.example.kalaha.GameStatistic
import com.example.kalaha.R
import com.example.kalaha.database.GameStatisticDatabase
import com.example.kalaha.database.GameStatisticDatabaseDao
import com.example.kalaha.databinding.FragmentGameBinding
import kotlinx.coroutines.launch

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private val viewModel: GameViewModel by viewModels()
    private lateinit var dataSource: GameStatisticDatabaseDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        var isChronometerRunning = false;

        val application = requireNotNull(this.activity).application
        dataSource = GameStatisticDatabase.getInstance(application).gameStatisticDatabaseDao


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
            saveToDatabase()
            val navController = findNavController()
            navController.navigateUp()
        }

        binding.home.setOnClickListener {
            val navController = findNavController()
            navController.navigateUp()
        }

        binding.restartButton.setOnClickListener{
            saveToDatabase()
            viewModel.onRestart()
        }

        return binding.root
    }

    fun saveToDatabase(){
        if(binding.nickname.text.isNotEmpty()){
            val tmp = GameStatistic(
                binding.nickname.text.toString(),
                binding.time.text.toString(),
                binding.score.text.toString())
            lifecycleScope.launch {
                dataSource.insert(tmp)
            }
        }
    }
}
package com.example.kalaha.statistics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kalaha.GameStatistic

class StatisticsViewModel:ViewModel() {
    private val _statisticList = MutableLiveData<List<GameStatistic>>()
    val statisticList: LiveData<List<GameStatistic>>
        get() = _statisticList


    init{
        _statisticList.value = listOf(
            GameStatistic("Oleg", "12:42","13:1"),
            GameStatistic("Sergey", "12:42","13:1"),
            GameStatistic("Misha", "12:42","13:1"),
            GameStatistic("Denis", "12:42","13:1"))
    }
}
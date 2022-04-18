package com.example.kalaha.statistics

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kalaha.GameStatistic
import com.example.kalaha.database.GameStatisticDatabaseDao

class StatisticsViewModel(dataSource: GameStatisticDatabaseDao, application: Application):ViewModel() {
    private val _statisticList = MutableLiveData<List<GameStatistic>>()
    val statisticList: LiveData<List<GameStatistic>>
        get() = _statisticList

    val gameStatistic = dataSource.getStatistic()

}
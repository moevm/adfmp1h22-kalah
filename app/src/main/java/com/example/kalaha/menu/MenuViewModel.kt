package com.example.kalaha.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MenuViewModel: ViewModel() {

    private val _navigateToSingleGame = MutableLiveData<Boolean>()
    val navigateToSingleGame: LiveData<Boolean>
        get() = _navigateToSingleGame

    fun onSingleClicked(){
        _navigateToSingleGame.value = true
    }

    fun onNavigatedToSingleGame(){
        _navigateToSingleGame.value = false
    }

    private val _navigateToMultiGame = MutableLiveData<Boolean>()
    val navigateToMultiGame: LiveData<Boolean>
        get() = _navigateToMultiGame

    fun onMultiClicked(){
        _navigateToMultiGame.value = true
    }

    fun onNavigatedToMultiGame(){
        _navigateToMultiGame.value = false
    }

    private val _navigateToOptions = MutableLiveData<Boolean>()
    val navigateToOptions: LiveData<Boolean>
        get() = _navigateToOptions

    fun onOptionsClicked(){
        _navigateToOptions.value = true
    }

    fun onNavigatedToOptions(){
        _navigateToOptions.value = false
    }

    private val _navigateToStatistics = MutableLiveData<Boolean>()
    val navigateToStatistics: LiveData<Boolean>
        get() = _navigateToStatistics

    fun onStatisticsClicked(){
        _navigateToStatistics.value = true
    }

    fun onNavigatedToStatistics(){
        _navigateToStatistics.value = false
    }

}
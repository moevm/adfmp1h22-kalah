package com.example.kalaha.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.kalaha.Game

class GameViewModel: ViewModel() {

    private var _game = Game(false)
    private var _gameLD = MutableLiveData<Game>(_game)
    val gameLD: LiveData<Game>
        get() = _gameLD


    fun onClick(index: Int){
        _gameLD.value = _game.apply {
            onHole(index)
        }
    }

}
package com.example.kalaha.game

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.kalaha.Game

class GameViewModel: ViewModel() {

    private var _game = Game(false)
    private var _gameLD = MutableLiveData(_game)
    val gameLD: LiveData<Game>
        get() = _gameLD

    private var _state = MutableLiveData(Game.State.FirstPlayerTurn)
    val state: LiveData<Game.State>
        get() = _state

    val isFPButtonsEnabled = Transformations.map(_state){
        it == Game.State.FirstPlayerTurn
    }

    val isSPButtonsEnabled = Transformations.map(_state){
        it == Game.State.SecondPlayerTurn
    }

    val isGameFinished = Transformations.map(_state){
        if (it == Game.State.EndOfGame) View.VISIBLE else View.GONE
    }

    val gameScore = Transformations.map(_gameLD){
        it.gameScore()
    }

    fun onHome(){

    }

    fun onRestart(){
        _game = Game(false)
        _gameLD.value = _game
        _state.value = Game.State.FirstPlayerTurn
    }

    fun onClick(index: Int){
        _gameLD.value = _game.apply {
            _state.value = onHole(index)
        }
    }
}
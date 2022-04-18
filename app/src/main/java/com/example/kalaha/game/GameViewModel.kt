package com.example.kalaha.game

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.kalaha.Game

class GameViewModel(application: Application): AndroidViewModel(application) {

    private var sharedPrefs: SharedPreferences =
        application.getSharedPreferences("Options", Context.MODE_PRIVATE)

    private var _game = Game(getFromPrefs())
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

    fun onBotTurn(){
        onClick(_game.botStep())
    }

    fun onRestart(){
        _game = Game(getFromPrefs())
        _gameLD.value = _game
        _state.value = Game.State.FirstPlayerTurn
    }

    fun onClick(index: Int){
        _gameLD.value = _game.apply {
            _state.value = onHole(index)

        }
    }

    fun getFromPrefs() = sharedPrefs.getInt("Handicap", 0)

}
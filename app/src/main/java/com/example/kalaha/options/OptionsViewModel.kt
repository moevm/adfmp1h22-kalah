package com.example.kalaha.options

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.*

class OptionsViewModel(application: Application) : AndroidViewModel(application) {

        private var sharedPrefs: SharedPreferences =
                application.getSharedPreferences("Options", Context.MODE_PRIVATE)

        private var _handicapValue = MutableLiveData<Int>()
        val handicapValue: LiveData<Int>
         get() = _handicapValue

        val isPlusButtonEnabled = Transformations.map(_handicapValue) {
                it < 6
        }

        val isMinusButtonEnabled = Transformations.map(_handicapValue){
                it > 0
        }

        fun onPlusButton(){
                _handicapValue.value = _handicapValue.value?.plus(1)
                saveToPrefs()
        }

        fun onMinusButton(){
                _handicapValue.value = _handicapValue.value?.minus(1)
                saveToPrefs()
        }

        init {
                _handicapValue.value = sharedPrefs.getInt("Handicap",0)
        }

        private fun saveToPrefs(){
                with (sharedPrefs.edit()) {
                        _handicapValue.value?.let { putInt("Handicap", it) }
                        apply()
                }
        }
}
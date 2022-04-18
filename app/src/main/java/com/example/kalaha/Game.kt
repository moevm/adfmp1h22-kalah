package com.example.kalaha

import android.util.Log

class Game(private val handicap: Int) {

    val holes = Array(14){ if (it == 6) 0 else if(it == 13) handicap else 4 }

    fun onHole(index: Int): State {

        // true - первый игрок, false - второй
        val player = index in 0..5

        // если выбрали пустую лунку, то продолжаем ход
        if (holes[index] == 0) return if(player) State.FirstPlayerTurn else State.SecondPlayerTurn

        // раскидываем камушки
        val tmp = holes[index]
        holes[index] = 0
        for (i in 1..tmp){
            holes[(index + i) % 14] ++
        }

        val endHoleIndex = (index + tmp) % 14


        // Если закончилось на нашем калахе, то продолжаем наш ход
        if (((endHoleIndex == 6 && player) || (endHoleIndex == 13 && !player)) && !isEndOfGame())
            return if(player) State.FirstPlayerTurn else State.SecondPlayerTurn

        // Если закончили на лунке, где было пусто, а в противоположной не пусто
        // то берем свой и противоположные камни
        if(player && endHoleIndex in 0..5 && holes[12 - endHoleIndex] != 0 && holes[endHoleIndex] == 1){
            holes[6] += 1 + holes[12 - endHoleIndex]
            holes[endHoleIndex] = 0
            holes[12 - endHoleIndex] = 0
        }
        else if(!player && endHoleIndex in 7..12 && holes[(12 - endHoleIndex) % 14] != 0 && holes[endHoleIndex] == 1){
            holes[13] += 1 + holes[(12 - endHoleIndex) % 14]
            holes[endHoleIndex] = 0
            holes[(12 - endHoleIndex) % 14] = 0
        }

        // проверяем на конец игры
        if (isEndOfGame()) return State.EndOfGame

        // передаем ход другому игроку
        return if(!player) State.FirstPlayerTurn else State.SecondPlayerTurn
    }

    fun isEndOfGame(): Boolean{
        if (holes.slice(0..5).all { it == 0 }){
            holes[13] += holes.slice(7..12).sum()
            for (i in 7..12)
                holes[i] = 0
            return true
        }
        else if (holes.slice(7..12).all { it == 0 }){
            holes[6] += holes.slice(0..5).sum()
            for (i in 0..5)
                holes[i] = 0
            return true
        }
        return false
    }

    fun gameScore():String{
        return holes[6].toString() + ":" + holes[13].toString()
    }

    fun botStep(): Int {
        val results = Array(6){res(7 + it)}
        val res = 7 + results.indices.maxByOrNull { results[it] }!!
        Log.i("Bot", "finished counting with $res")
        return res

    }

    fun res(index: Int): Int{

        if(holes[index] == 0) return -1
        val myHoles = holes.clone()
        val startValue = myHoles[13]

        val tmp = myHoles[index]
        myHoles[index] = 0
        for (i in 1..tmp){
            myHoles[(index + i) % 14] ++
        }

        val endHoleIndex = (index + tmp) % 14

        if(endHoleIndex in 7..12 && myHoles[(12 - endHoleIndex) % 14] != 0 && myHoles[endHoleIndex] == 1){
            myHoles[13] += 1 + myHoles[(12 - endHoleIndex) % 14]
            myHoles[endHoleIndex] = 0
            myHoles[(12 - endHoleIndex) % 14] = 0
        }
        return myHoles[13] - startValue
    }

    enum class State {
        EndOfGame, FirstPlayerTurn, SecondPlayerTurn
    }
}
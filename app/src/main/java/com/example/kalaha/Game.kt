package com.example.kalaha

class Game(val haveBot: Boolean) {

    val holes = Array(14){ if (it == 6 || it == 13) 0 else 4  }


    // true если зкаончился ход
    // false если ход продолжается
    fun onHole(index: Int): Boolean{

        // true - первый игрок, false - второй
        val player = index in 0..5

        if (holes[index] == 0) return false

        val tmp = holes[index]
        holes[index] = 0
        for (i in 1..tmp){
            holes[(index + i) % 14] ++
        }

        val endHoleIndex = (index + tmp) % 14

        if ((endHoleIndex == 6 && player) || (endHoleIndex == 13 && !player))
            return false


        if(player && endHoleIndex in 0..5 && holes[endHoleIndex] == 1){
            holes[6] += 1 + holes[12 - endHoleIndex]
            holes[endHoleIndex] = 0
            holes[12 - endHoleIndex] = 0
        }
        else if(!player && endHoleIndex in 7..12 && holes[endHoleIndex] == 1){
            holes[13] += 1 + holes[(12 -endHoleIndex) % 14]
            holes[endHoleIndex] = 0
            holes[(12 -endHoleIndex) % 14] = 0
        }

        return true
    }
}
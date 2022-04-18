package com.example.kalaha

import org.junit.Test
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GameUnitTest {
    @Test
    fun handicap_isCorrect() {
        assertEquals(4, Game(4).holes[13])
        assertEquals(2, Game(2).holes[13])
        assertEquals(7, Game(7).holes[13])
        assertEquals(1, Game(1).holes[13])
    }

    @Test
    fun gameInit_isCorrect(){
        assertEquals(arrayOf(4,4,4,4,4,4,0,4,4,4,4,4,4,0), Game(0).holes)
        assertEquals(arrayOf(4,4,4,4,4,4,0,4,4,4,4,4,4,5), Game(5).holes)
    }

    @Test
    fun isEndOfGame_isCorrect() {
        val game = Game(0)
        game.onHole(2)
        game.onHole(1)
        game.onHole(9)
        game.onHole(12)
        game.onHole(1)
        assertEquals(false, game.isEndOfGame())
        game.onHole(8)
        game.onHole(3)
        game.onHole(9)
        game.onHole(4)
        game.onHole(10)
        game.onHole(2)
        game.onHole(4)
        game.onHole(5)
        game.onHole(8)
        game.onHole(0)
        game.onHole(12)
        game.onHole(1)
        game.onHole(9)
        game.onHole(4)
        game.onHole(11)
        game.onHole(5)
        game.onHole(4)
        game.onHole(12)
        game.onHole(10)
        game.onHole(2)
        assertEquals(true, game.isEndOfGame())
    }
}
package aoc.day02

import aoc.Util
import org.junit.Assert.*
import org.junit.Test

class SubmarineTest{

    private val exampleInput = Util.readInput("/day02/example.txt")
    private val input = Util.readInput("/day02/input.txt")

    @Test
    fun example() {
        val answer = Submarine().move(exampleInput)
        assertEquals(150, answer)
    }

    @Test
    fun part01() {
        val answer = Submarine().move(input)
        println(answer)
    }

    @Test
    fun example2() {
        val answer = Submarine().move2(exampleInput)
        assertEquals(900, answer)
    }

    @Test
    fun part02() {
        val answer = Submarine().move2(input)
        println(answer)
    }
}
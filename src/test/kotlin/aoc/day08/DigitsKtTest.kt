package aoc.day08

import aoc.Util
import aoc.toListOfLong
import org.junit.Assert.*
import org.junit.Test

class DigitsKtTest{
    val exampleInput = Util.readInput("/day08/example.txt")
    val input = Util.readInput("/day08/input.txt")
    @Test
    fun example() {
        assertEquals(26, part1(exampleInput))
    }

    @Test
    fun part1() {
        println(part1(input))
    }
}
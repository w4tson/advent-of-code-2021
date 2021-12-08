package aoc.day08

import aoc.Util
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

    @Test
    fun example2() {
        part2(exampleInput)
    }
}
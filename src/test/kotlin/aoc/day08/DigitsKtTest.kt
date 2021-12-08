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
        assertEquals(5353, countDigit("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"))
    }

    @Test
    fun example21() {
        assertEquals(61229, exampleInput.lines().map { countDigit(it) }.sum())
    }

    @Test
    fun part2() {
        println(input.lines().map { countDigit(it) }.sum())
    }
}
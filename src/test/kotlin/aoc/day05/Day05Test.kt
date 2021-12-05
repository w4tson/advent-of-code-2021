package aoc.day05

import aoc.Util
import org.junit.Assert.*
import org.junit.Test

class Day05Test {
    val exampleInput = Util.readInput("/day05/example.txt")
    val input = Util.readInput("/day05/input.txt")

    @Test
    fun example() {
        assertEquals(5, Day05().part1(exampleInput))
    }

    @Test
    fun part1() {
        println(Day05().part1(input))
    }

    @Test
    fun part2() {
        println(Day05().part1(input, false))
    }
}
package aoc.day01

import aoc.Util.Companion.readInput
import org.junit.Test
import kotlin.test.assertEquals

internal class Day01Test {

    val exampleInput = readInput("/day01/example.txt")
    val input = readInput("/day01/input.txt")
    
    @Test
    internal fun example() {
        val count = Scanner(toArr(exampleInput)).count()
        assertEquals(7, count)
    }

    @Test
    fun part1() {
        val count = Scanner(toArr(input)).count()
        println(count)
    }

    @Test
    fun part2Example() {
        val count = Scanner(toArr(exampleInput)).part2()
        assertEquals(5, count)
    }

    @Test
    fun part2() {
        val count = Scanner(toArr(input)).part2()
        println(count)
    }

    fun toArr(input: String) = input.lines().map { it.toInt() }
    
}
package aoc.day15

import aoc.Util
import org.junit.Test
import kotlin.test.assertEquals

class Day15Test{
    val example = Util.readInput("/day15/example.txt")
    val input = Util.readInput("/day15/input.txt")

    @Test
    fun example() {
        val cave = Cave(example)
        val totalRisk = cave.totalRisk()
        assertEquals(40, totalRisk)
        cave.display()
    }

    @Test
    fun part1() {
        val totalRisk = Cave(input).totalRisk()
        println(totalRisk)
    }

    @Test
    fun part2Example() {
        val cave = Cave(example, 5)
        val totalRisk = cave.totalRisk()
        cave.display()
    }

    @Test
    fun part2() {
        val cave = Cave(input, 5)
        val totalRisk = cave.totalRisk()
        cave.display()
        println(totalRisk)
    }
}
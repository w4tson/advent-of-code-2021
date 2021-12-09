package aoc.day09

import aoc.Util
import org.junit.Assert.*
import org.junit.Test

class Day09Test {
    val exampleInput = Util.readInput("/day09/example.txt")
    val input = Util.readInput("/day09/input.txt")
    
    @Test
    fun example() {
        val grid = exampleInput.toGrid()
        assertEquals(15, HeatMap(grid).riskLevel())
    }

    @Test
    fun part1() {
        val grid = input.toGrid()
        val res = HeatMap(grid).riskLevel()
        println(res)
    }

    @Test
    fun example2() {
        assertEquals(1134, HeatMap(exampleInput.toGrid()).productOfTopThree())
    }

    @Test
    fun part2() {
        val res = HeatMap(input.toGrid()).productOfTopThree()
        println(res)
    }
}


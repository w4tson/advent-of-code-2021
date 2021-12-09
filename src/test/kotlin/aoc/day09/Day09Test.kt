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
        HeatMap(grid).display()
    }

    @Test
    fun part1() {
        val grid = input.toGrid()
        val res = HeatMap(grid).riskLevel()
        println(res)
    }

    @Test
    fun example2() {
        val grid = exampleInput.toGrid()
        assertEquals(1134, HeatMap(grid).topThreeBasins())
    }

    @Test
    fun part2() {
        val grid = input.toGrid()
        val res = HeatMap(grid).topThreeBasins()
        println(res)
    }

    @Test
    fun adsf() {
        val one = HeatMap(exampleInput.toGrid()).findBasin(Coord(1, 0), emptySet())
        val two = HeatMap(exampleInput.toGrid()).findBasin(Coord(9, 0), emptySet())
        println(one)
        println(two)
    }
}


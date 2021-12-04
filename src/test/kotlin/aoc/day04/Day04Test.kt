package aoc.day04

import aoc.Util
import org.junit.Assert.*
import org.junit.Test

class Day04Test {
    val exampleInput = Util.readInput("/day04/example.txt")
    val input = Util.readInput("/day04/input.txt")

    @Test
    fun exmaple() {
        val bingoSubystem = exampleInput.toBingoSubystem()
        bingoSubystem.callAll()

    }

    @Test
    fun part1() {
        val toBingoSubystem = input.toBingoSubystem()
        toBingoSubystem.callAll()

    }
}
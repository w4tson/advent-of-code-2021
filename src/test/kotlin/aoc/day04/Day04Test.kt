package aoc.day04

import aoc.Util
import org.junit.Test

class Day04Test {
    val exampleInput = Util.readInput("/day04/example.txt")
    val input = Util.readInput("/day04/input.txt")

    @Test
    fun exmaple() {
        val bingoSubystem = exampleInput.toBingoSubystem()
        bingoSubystem.findFirstWinner()

    }

    @Test
    fun part1() {
        val toBingoSubystem = input.toBingoSubystem()
        toBingoSubystem.findFirstWinner()

    }

    @Test
    fun example2() {
        val bingoSubystem = exampleInput.toBingoSubystem()
        bingoSubystem.findLastWinner()
    }

    @Test
    fun part2() {
        val bingoSubystem = input.toBingoSubystem()
        bingoSubystem.findLastWinner()
    }
}
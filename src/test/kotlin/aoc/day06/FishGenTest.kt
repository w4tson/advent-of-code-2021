package aoc.day06

import aoc.Util
import aoc.toListOfLong
import org.junit.Assert.*
import org.junit.Test

class FishGenTest{

    val exmapleInput = "3,4,3,1,2".toListOfLong()
    val input = Util.readInput("/day06/input.txt").toListOfLong()
    
    @Test
    fun exqmple() {
        assertEquals(26, fishSimulSeq2(exmapleInput).take(19).last().count())
        assertEquals(5934, fishSimulSeq2(exmapleInput).take(81).last().count())
    }

    @Test
    fun part1() {
        println(fishSimulSeq(input).take(81).last().count())
    }

    @Test
    fun example2() {
        assertEquals(26984457539L, part2(exmapleInput))
    }

    @Test
    fun part2() {
        println(part2(input))
    }
}
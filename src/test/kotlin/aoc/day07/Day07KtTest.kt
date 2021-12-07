package aoc.day07

import aoc.Util
import aoc.toListOfInt
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class Day07KtTest{
    val example = Arrays.asList(16, 1, 2, 0, 4, 2, 7, 1, 2, 14)
    val input = Util.readInput("/day07/input.txt").toListOfInt()
    
    @Test
    fun example() {
        assertEquals(37, part1(example))
    }

    @Test
    fun part1() {
        println(part1(input))
    }
}
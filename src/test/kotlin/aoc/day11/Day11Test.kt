package aoc.day11

import aoc.Util
import org.junit.Test
import kotlin.test.assertEquals

class Day11Test {
    
    val example = Util.readInput("/day11/example.txt")
    val largerExample = Util.readInput("/day11/largerExample.txt")
    val input = Util.readInput("/day11/input.txt")
    
    @Test
    fun example() {
        Day11(example.toOctopi()).genSeq().take(3).forEach { display(it) }
    }

    @Test
    fun largerExample() {
        assertEquals(1656, part1(largerExample))
    }

    @Test
    fun part1() {
        println(part1(input))
    }

    @Test
    fun largerExamplePart2() {
        assertEquals(195, part2(largerExample))
    }

    @Test
    fun part2() {
        println(part2(input))
    }
}
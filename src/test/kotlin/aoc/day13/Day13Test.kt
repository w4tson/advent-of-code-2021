package aoc.day13

import aoc.Util
import org.junit.Assert.*
import org.junit.Test

class Day13Test{
    
    val example = Util.readInput("/day13/example.txt")
    val input = Util.readInput("/day13/input.txt")
    
    @Test
    fun example() {
        assertEquals(17, part1(example))
    }

    @Test
    fun part1() {
        println(part1(input))
    }

    @Test
    fun name() {
        part2(input)
    }
}


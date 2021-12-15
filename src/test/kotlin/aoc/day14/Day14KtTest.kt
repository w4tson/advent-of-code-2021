package aoc.day14

import aoc.Util
import org.junit.Test
import kotlin.test.assertEquals

class Day14KtTest{
    val example = Util.readInput("/day14/example.txt")
    val input = Util.readInput("/day14/input.txt")
    
    @Test
    fun name() {
//        Polymers(input).polymerSequence().take(5).forEach { println(it) }
        assertEquals(1588, Polymers(example).part1())
    }

    @Test
    fun part1() {
        println(Polymers(input).part1())
    }

    @Test
    fun part2() {
        println(Polymers(input).part2(40))
    }
    
    @Test
    fun testing() {
        println("NBBBCNCCNBBNBNBBCHBHHBCHB".groupingBy { it }.eachCount())
        assertEquals(1588L, Polymers(example).part2(10))
    }
}


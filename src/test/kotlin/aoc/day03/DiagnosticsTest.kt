package aoc.day03

import aoc.Util
import org.junit.Assert.*
import org.junit.Test

class DiagnosticsTest {
    val exampleInput = Util.readInput("/day03/example.txt")
    val input = Util.readInput("/day03/input.txt")

    
    @Test
    fun example() {
        val d = Diagnostics(exampleInput)
        assertEquals('1', d.mostCommonBit("11100"))
        assertEquals('0', d.mostCommonBit("1110000"))
        assertEquals("10110", d.calculateGammaString(exampleInput))
        assertEquals(9, d.calculateEpsilon("10110"))
        val powerConsumption = d.powerConsumption()
        assertEquals(198, powerConsumption)
    }

    @Test
    fun part1() {
        val d = Diagnostics(input)
        val consumption = d.powerConsumption()
        println(consumption)
    }
}
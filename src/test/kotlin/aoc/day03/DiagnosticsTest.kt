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
        assertEquals('1', mostCommonBit("11100"))
        assertEquals('0', mostCommonBit("1110000"))
        assertEquals("10110", d.calculateGammaString())
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

    @Test
    fun example2() {
        val d = Diagnostics(exampleInput)
        val oxygenGeneratorRating = d.oxygenGeneratorRating()
        val co2ScrubberRating = d.co2ScrubberRating()
        assertEquals(23, oxygenGeneratorRating)
        assertEquals(10, co2ScrubberRating)
    }

    @Test
    fun testCommonFuncs() {
        val leastCommonBit = leastCommonBit("011110011100")
        val mostCommon = mostCommonBit("000", '1')
        assertEquals('0', leastCommonBit)
        assertEquals('0', mostCommon)
    }

    @Test
    fun part2() {
        println(Diagnostics(input).lifeSupportRating())
    }
}
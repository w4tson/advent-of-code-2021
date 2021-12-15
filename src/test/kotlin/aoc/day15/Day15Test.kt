package aoc.day15

import aoc.Util
import org.junit.Assert.*
import org.junit.Test

class Day15Test{
    val example = Util.readInput("/day15/example.txt")

    @Test
    fun example() {
        println(example)
        val path = Cave(example).findLeastRiskyPath()
        println(path)
    }
}
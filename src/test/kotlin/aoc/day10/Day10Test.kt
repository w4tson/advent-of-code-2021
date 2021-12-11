package aoc.day10

import aoc.Util
import org.junit.Assert.assertEquals
import org.junit.Test

class Day10Test {

    val input = Util.readInput("/day10/input.txt")

    val exampleInput = """
        [({(<(())[]>[[{[]{<()<>>
        [(()[<>])]({[<{<<[]>>(
        {([(<{}[<>[]}>{[]{[(<()>
        (((({<>}<{<{<>}{[]{[]{}
        [[<[([]))<([[{}[[()]]]
        [{[{({}]{}}([{[{{{}}([]
        {<[[]]>}<{[{[{[]{()[[[]
        [<(<(<(<{}))><([]([]()
        <{([([[(<>()){}]>(<<{{
        <{([{{}}[<[[[<>{}]]]>[]]
    """.trimIndent()
    
    @Test
    fun example() {
        assertEquals('}', isCorrupt("{([(<{}[<>[]}>{[]{[(<()>"))
        assertEquals(')', isCorrupt("[[<[([]))<([[{}[[()]]]"))
        assertEquals(']', isCorrupt("[{[{({}]{}}([{[{{{}}([]"))
        assertEquals(')', isCorrupt("[<(<(<(<{}))><([]([]()"))
        assertEquals('>', isCorrupt("<{([([[(<>()){}]>(<<{{"))
        assertEquals(26397, corruptionScore(exampleInput))
    }

    @Test
    fun part1() {
        println(corruptionScore(input))
    }
}

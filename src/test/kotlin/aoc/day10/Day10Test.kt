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

    @Test
    fun example2() {
        assertEquals("}}]])})]", isIncomplete("[({(<(())[]>[[{[]{<()<>>"))
        assertEquals(")}>]})", isIncomplete("[(()[<>])]({[<{<<[]>>(")  )
        assertEquals("}}>}>))))", isIncomplete("(((({<>}<{<{<>}{[]{[]{}"))  
        assertEquals("]]}}]}]}>", isIncomplete("{<[[]]>}<{[{[{[]{()[[[]"))  
        assertEquals("])}>", isIncomplete("<{([{{}}[<[[[<>{}]]]>[]]")  )

        assertEquals(288957, calcCompletionScore("}}]])})]"))
        assertEquals(5566, calcCompletionScore(")}>]})"))
        assertEquals(1480781, calcCompletionScore("}}>}>))))"))
        assertEquals(995444, calcCompletionScore("]]}}]}]}>"))
        assertEquals(294, calcCompletionScore("])}>"))

        assertEquals(288957, completionScore(exampleInput))
    }

    @Test
    fun part2() {
        println(completionScore(input))
    }
}

package aoc.day12

import aoc.Util
import org.junit.Assert.*
import org.junit.Test

class Day12Test {
    val exampleInput = """
        start-A
        start-b
        A-c
        A-b
        b-d
        A-end
        b-end
    """.trimIndent()
    
    val expectedExample = """
        start,A,b,A,c,A,end
        start,A,b,A,end
        start,A,b,end
        start,A,c,A,b,A,end
        start,A,c,A,b,end
        start,A,c,A,end
        start,A,end
        start,b,A,c,A,end
        start,b,A,end
        start,b,end
    """.trimIndent().lines().toSet()
    
    val exampleInput2 = """
        dc-end
        HN-start
        start-kj
        dc-start
        dc-HN
        LN-dc
        HN-end
        kj-sa
        kj-HN
        kj-dc
    """.trimIndent()
    
    val expectedExample2 = """
        start,HN,dc,HN,end
        start,HN,dc,HN,kj,HN,end
        start,HN,dc,end
        start,HN,dc,kj,HN,end
        start,HN,end
        start,HN,kj,HN,dc,HN,end
        start,HN,kj,HN,dc,end
        start,HN,kj,HN,end
        start,HN,kj,dc,HN,end
        start,HN,kj,dc,end
        start,dc,HN,end
        start,dc,HN,kj,HN,end
        start,dc,end
        start,dc,kj,HN,end
        start,kj,HN,dc,HN,end
        start,kj,HN,dc,end
        start,kj,HN,end
        start,kj,dc,HN,end
        start,kj,dc,end
    """.trimIndent().lines().toSet()
    
    val example3Input = Util.readInput("/day12/example3.txt")
    val expectedExamplePart2 = Util.readInput("/day12/expectedExamplePart2.txt").lines().toSet()
    val input = Util.readInput("/day12/input.txt")
    
    @Test
    fun example() {
        assertEquals(Day12(exampleInput).part1(), expectedExample)
    }

    @Test
    fun example2() {
        val res = Day12(exampleInput2).part1()
        assertEquals(res, expectedExample2)
    }

    @Test
    fun example3() {
        assertEquals(226, Day12(example3Input).part1().size)
    }

    @Test
    fun part1() {
        println(Day12(input).part1().size)
    }

    @Test
    fun examplePart2() {
        assertEquals(expectedExamplePart2, Day12(exampleInput).part2())
    }

    @Test
    fun part2() {
        println(Day12(input).part2().size)
    }
}


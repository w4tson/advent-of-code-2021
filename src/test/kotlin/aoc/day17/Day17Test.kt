package aoc.day17

import org.junit.Assert.*
import org.junit.Test

class Day17Test {
    val example1 = Day17("target area: x=20..30, y=-10..-5")
    val part1 = Day17("target area: x=257..286, y=-101..-57")
    
    @Test
    fun example() {
        val t = example1.calcTrajectory(6, 3)
        t.take(10).forEach { println(it) }
        assertEquals(true, example1.isAHit(t))
        assertEquals(true, example1.isAHit(example1.calcTrajectory(6, 3)))
        assertEquals(true, example1.isAHit(example1.calcTrajectory(9, 0)))
        assertEquals(false, example1.isAHit(example1.calcTrajectory(17, -4)))

        assertEquals(3, example1.maxHeightOftrajectory(example1.calcTrajectory(7,2)))
        assertEquals(6, example1.maxHeightOftrajectory(example1.calcTrajectory(6,3)))

        assertEquals(45, example1.maxY())
    }

    @Test
    fun part1() {
        println(part1.maxY())
    }

    @Test
    fun part2Example() {
        assertEquals(112, example1.findAllTrajectories().count())
    }

    @Test
    fun part2() {
        println(part1.findAllTrajectories().count())
    }
}
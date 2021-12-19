package aoc.day17

import aoc.Coord
import aoc.plus
import aoc.rangeTo
import java.lang.Integer.max
import java.lang.Math.abs

class Day17(input: String) {
    
    val targetX : IntRange
    val targetY : IntRange
    val origin = Coord(0,0)
    val gravity = Coord(0,-1)
    init {
        val (x,y) = input.replace("target area: ", "").split(", ")
        val (fromX, toX) = x.drop(2).split("..")
        val (fromY, toY) = y.drop(2).split("..")
        targetX = Integer.parseInt(fromX)..Integer.parseInt(toX)
        targetY = Integer.parseInt(fromY)..Integer.parseInt(toY)
    }
    
    fun maxY() : Int  {
        return findAllTrajectories().map { maxHeightOftrajectory(calcTrajectory(it)) }.max()!!
    }
    
    fun findAllTrajectories() : List<Coord> {
        val n = max(targetX.last, abs(targetY.last))
        return (Coord(-n, -n)..Coord(n,n))
            .filter { isAHit(calcTrajectory(it)) }
    }
    
    fun maxHeightOftrajectory(trajectory: Sequence<Coord>) : Int {
        val t = trajectory.zipWithNext().takeWhile { (prev, it) -> it.y > prev.y }
        return if (t.iterator().hasNext()) t.last().second.y else 0
    }

    fun calcTrajectory(x : Int, y : Int) : Sequence<Coord> = calcTrajectory(Coord(x,y))

    fun calcTrajectory(velocity : Coord) : Sequence<Coord> {
        return generateSequence(Pair(origin, velocity)) { (pos, v) ->
            val newPos  = pos + v
            val drag = calcDrag(v)
            Pair(newPos, v + drag + gravity)
        }.map { it.first }
    }
    
    fun isAHit(trajectory : Sequence<Coord>) : Boolean {
        return trajectory.takeWhile { it.x <= targetX.last && it.y >= targetY.first }.any {  coordInTarget(it) }
    }
    
    fun coordInTarget(coord: Coord) : Boolean {
        return targetX.contains(coord.x) && targetY.contains(coord.y)
    }
       
    fun calcDrag(coord: Coord) : Coord = when {
        coord.x == 0 -> origin
        coord.x > 0 -> Coord(-1,0)
        else -> Coord(1,0)
    }
    
}

package aoc.day05

import java.lang.Integer.min
import java.lang.Math.abs
import java.lang.Math.max

class Day05 {
    fun part1(input : String, excDiagonals : Boolean = true) : Int {
        var counts = mutableMapOf<Coord, Int>()
        input.lines().forEach { 
            val coords = it.toCoordList(excDiagonals)
            coords.forEach{
                counts.compute(it) { k,v -> 
                    if (v == null) 1 else v+1
                }
            }
        }
        
        return counts.filter { (k,v) -> v>1 }.count()
    }
}

data class Coord(val x: Int, val y : Int)

fun String.toCoordList(excludeDiagnoals : Boolean = true) : List<Coord> {
    val (left, right) = this.split(" -> ")
    val (x1,y1) = left.split(",").map { it.toInt() }
    val (x2,y2) = right.split(",").map { it.toInt() }
    
    if (excludeDiagnoals && x1 != x2 && y1 != y2) return emptyList()
    
    val lineLength = max(abs(x2-x1), abs(y2-y1)) + 1
    
    return coordSeq(x1,x2).zip(coordSeq(y1,y2)).take(lineLength).map { Coord(it.first, it.second) }.toList()
}

fun coordSeq(from : Int, to : Int) : Sequence<Int> = generateSequence(from) { it + max(-1, min(to - from, 1)) }
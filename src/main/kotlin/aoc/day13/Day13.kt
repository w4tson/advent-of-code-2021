package aoc.day13

import java.lang.Integer.parseInt

data class Coord(val x : Int, val y: Int)
enum class Axis { X, Y }
typealias Fold = Pair<Axis, Int>

class Paper(val points : Map<Coord, Boolean>) {
    
    fun fold(fold : Fold) : Paper {
        val length = fold.second * 2  
        
        val folded = if (fold.first == Axis.X) {
            val left = points.filter { it.key.x < fold.second }
            val right = points.filter { it.key.x > fold.second }
                .map { (coord, b) ->  Pair(Coord(length - coord.x, coord.y), b)}.toMap()
            left + right
        } else {
            val top = points.filter { it.key.y < fold.second }
            val bottom = points.filter { it.key.y > fold.second }
                .map { (coord, b) ->  Pair(Coord(coord.x, length -coord.y), b)}.toMap()
            top + bottom
        }
        
        return Paper(folded)  
    }
    
    fun display() {
        val minX = points.keys.minBy { it.x }!!.x
        val minY = points.keys.minBy { it.y }!!.y
        val maxX = points.keys.maxBy { it.x }!!.x
        val maxY = points.keys.maxBy { it.y }!!.y
        
        (minY..maxY).forEach { y ->
            (minX..maxX).forEach {  x-> 
                if (points.getOrDefault(Coord(x,y), false)) {
                    print("#")
                } else {
                    print(" ")
                }
            }
            println()
        }
    }
}

fun part1(input: String) : Int {
    val folds = input.toFolds()
    val paper = Paper(input.toPoints()).fold(folds[0])
    return paper.points.keys.count()
}

fun part2(input: String) {
    input.toFolds().fold(Paper(input.toPoints())) { paper, fold -> paper.fold(fold) }.display()
}

fun String.toPoints() : Map<Coord, Boolean> {
    return this.lines().takeWhile { it.length > 0 }
        .map { val (x,y) = it.split(","); Pair(Coord(parseInt(x), parseInt(y)), true) }
        .toMap()
}

fun String.toFolds() : List<Fold> {
    return this.lines()
        .dropWhile { it.length ==0 || it[0].isDigit() }
        .takeWhile { it.startsWith("fold") }
        .map { val (axis, value) = it.replaceFirst("fold along ", "").split("="); Pair(Axis.valueOf(axis), parseInt(value)) }
}
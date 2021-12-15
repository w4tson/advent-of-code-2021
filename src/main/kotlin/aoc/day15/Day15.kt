package aoc.day15

import java.util.*

typealias Grid = Array<Array<Int>>
data class Coord(val x: Int, val y : Int)
typealias Path = List<Coord>

class Cave(val input : String) {
    val grid: Grid = input.toGrid()

    private var width: Int = grid[0].size
    private var height: Int = grid.size

    fun surroundingCoords(pos: Coord): List<Coord> {
        return neighbours.map { (a, b) -> Coord(a + pos.x, b + pos.y) }
            .filter { withinBounds(it) }
    }

    fun withinBounds(pos: Coord): Boolean = pos.x in 0 until width && pos.y >= 0 && pos.y < height

//    fun findLeastRiskyPath(): List<Path> {
//        return findPath(Coord(0,0), listOf(Coord(0,0)), listOf(), listOf())
//    }

    fun riskOfPath(path: Path): Int {
        if (path.isEmpty()) return Int.MAX_VALUE

        return path.sumBy { grid[it.y][it.x] }
    }


    fun findLeastRiskyPath() {
        val paths = mutableListOf<Path>()
        val locations = Stack<Coord>()
        locations.push(Coord(0, 0))
        var leastRiskyPath = listOf<Coord>()
        val visited = mutableListOf<Path>()

        while (!locations.isEmpty()) {

            val location = locations.pop()
            val newPaths = paths.map { it + location }.filter { !visited.contains(it) }
            visited.addAll(newPaths)

            if (location == Coord(width - 1, height - 1)) {
                leastRiskyPath = newPaths.fold(leastRiskyPath) { acc, it ->
                    if (riskOfPath(acc) < riskOfPath(it)) {
                        acc
                    } else {
                        it
                    }
                }
            }

            surroundingCoords(location).forEach { locations.push(it) }
        }

//    fun findPath(location: Coord, longPaths: List<Path>, currentPaths: List<Path>, path : Path) : Path {
//        val newPaths = currentPaths.map { it + location }.filter { !longPaths.contains(it) }
//
//        if (location == Coord(width-1, height-1)) {
//            val leastRiskyPathTotal = riskOfPath(path)
//            val leastRiskyPath = newPaths.fold(path) { acc, it ->
//                if (riskOfPath(acc) < riskOfPath(it)) {
//                    acc
//                } else {
//                    it
//                }
//            }
//
//
//            val leastRiskiestPath = riskOfPath(path)
//
//            val pathToRisk = newPaths.associate { Pair(it, riskOfPath(it)) }
//            val minPath = pathToRisk.minBy { it.value }!!
//            val newLeastRiskiest = if (minPath.value < leastRiskiestPath) {
//                minPath.key
//            } else {
//                path
//            }
//
//            val seen = surroundingCoords(location)
//            val potentials = seen.filter { !visited.contains(it) }
//
//            potentials.map { findPath(it, visited + location + it, currentPath + it, paths) }
//            val next = potentials.minBy { grid[it.y][it.x] }!!
//
//            return findPath(next, visited + seen, path + next)
//        }
    }
}

fun String.toGrid() : Grid {
    return this.lines().map { it.toCharArray().map { Integer.parseInt("$it") }.toTypedArray() }.toTypedArray()
}

val neighbours = listOf(
    Coord(0, -1),
    Coord(0, +1),
    Coord(-1, 0),
    Coord(+1, 0)
)
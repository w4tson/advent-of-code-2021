package aoc.day11

import java.util.*

typealias Coord = Pair<Int, Int>


class Day11(val octopi : Octopi) {

    private var width : Int = octopi[0].size
    private var height : Int = octopi.size

    val coords: List<Coord> = (0 until height).flatMap { y -> (0 until width).map { x -> Coord(x,y) } }

    fun surroundingCoords(pos : Coord) : List<Coord> {

        return listOf(
            Coord(0,-1),
            Coord(0,+1),
            Coord(-1,0),
            Coord(+1,0),
            Coord(+1,-1),
            Coord(+1,+1),
            Coord(-1,+1),
            Coord(-1,-1)
        )
        .map { (a,b) -> Coord(pos.first + a, pos.second + b) }
        .filter(this::withinBounds)
    }

    fun withinBounds(pos: Coord) : Boolean = pos.first in 0 until width && pos.second >= 0 && pos.second < height
    
    fun genSeq() : Sequence<Octopi> {
        return generateSequence(octopi) {
            val flashes = Stack<Coord>()
            val flashed = mutableListOf<Coord>()
            coords.forEach { coord ->
                if (!flashed.contains(coord)) {
                    octopi[coord.second][coord.first] += 1
                    if (octopi[coord.second][coord.first] > 9 ) { flashes.push(coord); flashed.add(coord) }
                }
            }   
            
            while (flashes.isNotEmpty()) {
                val coord = flashes.pop()
                surroundingCoords(coord)
                    .filter { !flashed.contains(it) }
                    .forEach {
                        if (!flashed.contains(it)) {
                            octopi[it.second][it.first] += 1
                            if (octopi[it.second][it.first] > 9) {
                                flashes.push(it); flashed.add(it)
                            }
                        }
                }
            }

            flashed.forEach{ octopi[it.second][it.first] = 0 }
            
            it
        }
    }
}
typealias Octopi = Array<Array<Int>>

fun String.toOctopi() : Octopi {
    return this.lines().map { it.toCharArray().map { Integer.parseInt("$it") }.toTypedArray() }.toTypedArray()
}

val ANSI_CYAN = "\u001B[36m"
val ANSI_RESET = "\u001B[0m"

fun display(octopi: Octopi) {
    octopi.forEach {
        it.forEach {
            if (it == 0) {
                print("${ANSI_CYAN}$it${ANSI_RESET}")
            } else {
                print(it)
            }
        }
        println()
    }
    println()
}

fun countFlashes(octopi: Octopi) : Int {
    return octopi.sumBy { row -> row.filter { it == 0 }.count() }
}

fun part1(octopi: String) : Int = Day11(octopi.toOctopi()).genSeq().drop(1).take(100).map { countFlashes(it) }.sum()
fun part2(octopi: String) : Int = Day11(octopi.toOctopi()).genSeq().drop(1).takeWhile { countFlashes(it) != 100 }.count() +1
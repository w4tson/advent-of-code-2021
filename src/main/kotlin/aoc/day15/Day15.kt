package aoc.day15

import aoc.repeatRange

typealias Grid = Array<Array<Int>>
data class Coord(val x: Int, val y : Int)
typealias Path = List<Coord>



class Cave(val input : String, multiplier : Int = 1) {
    private val grid: Grid = input.toGrid()

    private val originalWidth = grid[0].size
    private val originalHeight = grid.size
    
    private var width: Int = originalWidth * multiplier
    private var height: Int = originalHeight * multiplier
    val origin = Coord(0, 0)
    val destination = Coord(width - 1, height - 1)
    var leastRiskyPath : List<Coord> = listOf()
    val ANSI_CYAN = "\u001B[36m"
    val ANSI_RESET = "\u001B[0m"

    fun surroundingCoords(pos: Coord): List<Coord> {
        return neighbours.map { (a, b) -> Coord(a + pos.x, b + pos.y) }
            .filter { withinBounds(it) }
    }

    fun withinBounds(pos: Coord): Boolean = pos.x in 0 until width && pos.y >= 0 && pos.y < height

    fun totalRisk() : Int = findLeastRiskyPath().drop(1).sumBy { getRiskAt(it) }
    
    fun getRiskAt(coord: Coord) : Int {
        val originalRisk = grid[coord.y % originalHeight][coord.x % originalWidth]
        val inc = coord.y / originalHeight + coord.x / originalWidth
        return repeatRange(1,9).take(originalRisk + inc).last()
    }

    fun findLeastRiskyPath(): List<Coord> {
        val unsettled = mutableSetOf(origin)
        val weights = mutableMapOf(origin to 0)
        val settled = mutableSetOf<Coord>()
        val parent = mutableMapOf<Coord, Coord>()

        while (unsettled.isNotEmpty()) {
            val location = unsettled.minBy { weights.getOrDefault(it, Int.MAX_VALUE) }!!  
            val currentWeight = weights.getOrDefault(location, Int.MAX_VALUE)
            settled.add(location)

            surroundingCoords(location)
                .filter{ !settled.contains(it) }
                .forEach {
                    unsettled.add(it)
                    val w = weights.getOrDefault(it, Int.MAX_VALUE)
                    val newWeight = currentWeight + getRiskAt(it)
                    if (w > newWeight) {
                        weights[it] = newWeight
                        parent[it] = location
                    }
                }

            if (location == destination)
                return printShortestPath(parent, location);
            
            unsettled.remove(location)

        }
        return emptyList()
    }
    
    fun printShortestPath(parent: Map<Coord,Coord>, start: Coord) : List<Coord> {
        val leastRiskyPath = mutableListOf(start)

        do  {
            val node = leastRiskyPath.last()
            parent[node]?.let { leastRiskyPath.add(it) }
            
        } while (node != origin)
        this.leastRiskyPath = leastRiskyPath
        
        return leastRiskyPath.reversed()
    }
    
    fun display() {
        (0 until height).forEach { y ->
            (0 until width).forEach { x ->
                val color = if (leastRiskyPath.contains(Coord(x,y)) ) { ANSI_CYAN } else { "" }
                print("${color}${getRiskAt(Coord(x,y))}$ANSI_RESET")
            }
            println()
        }
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
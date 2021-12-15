package aoc.day15

typealias Grid = Array<Array<Int>>
data class Coord(val x: Int, val y : Int)
typealias Path = List<Coord>

class Cave(val input : String) {
    val grid : Grid = input.toGrid()

    private var width: Int = grid[0].size
    private var height: Int = grid.size

    fun surroundingCoords(pos: Coord): List<Coord> {
        return neighbours.map { (a, b) -> Coord(a + pos.x, b + pos.y) }
            .filter { withinBounds(it) }
    }

    fun withinBounds(pos: Coord): Boolean = pos.x in 0 until width && pos.y >= 0 && pos.y < height
    
    fun findLeastRiskyPath(): List<Path> {
        return findPath(Coord(0,0), listOf(Coord(0,0)), listOf(), listOf())
    }
    
    fun riskOfPath(path: Path) : Int {
        return path.sumBy { grid[it.y][it.x] }
    }
     
    fun findPath(location: Coord, visited: List<Coord>, currentPath: Path, paths : List<Path>) : List<Path> {
        if (location == Coord(width-1, height-1)) {
            val leastRiskiestPath = paths.map { riskOfPath(it) }.min().or(0)
            if (riskOfPath(currentPath) < leastRiskiestPath)
            return paths + listOf(currentPath)
        }
        val seen = surroundingCoords(location)
        val potentials = seen.filter { !visited.contains(it) }
            
        potentials.map { findPath(it, visited + location + it, currentPath + it, paths) }
        val next = potentials.minBy { grid[it.y][it.x] }!!
        
        return findPath(next, visited + seen, path + next)
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
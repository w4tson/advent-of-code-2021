package aoc.day09

typealias Grid = Array<Array<Int>>
typealias Coord = Pair<Int, Int>

class HeatMap(val grid : Grid) {

    private var width : Int = grid[0].size
    private var height : Int = grid.size

    val neighbours = listOf(
        Coord(0, -1),
        Coord(0, +1),
        Coord(-1, 0),
        Coord(+1, 0)
    )

    fun productOfTopThree() : Int {
        val res =  basins().sorted().reversed().take(3)
        return res.get(0) * res.get(1) * res.get(2)
    }

    fun basins() : List<Int> = findLowPoints().map { findBasin(it, emptySet()).count() }
            
    fun riskLevel() : Int{
        var risk = 0
        (0 until height).map{ y ->
            (0 until width).map{ x -> risk += riskLevelAt(x,y) }.toTypedArray()
        }.toTypedArray()
        
        return risk
    }
    
    fun findLowPoints() : List<Coord> {
        val lowPoints = mutableListOf<Coord>()
        (0 until height).forEach{ y ->
            (0 until width).forEach{ x -> if (riskLevelAt(x,y) > 0) lowPoints.add(Coord(x,y)) }
        }
        
        return lowPoints
    }
    
    fun riskLevelAt(x : Int ,y: Int) : Int {
        return if (lowerThanNeighbours(Coord(x,y))) {
            grid[y][x] + 1
        } else {
            0
        }
    }
    
    fun lowerThanNeighbours(pos : Coord) : Boolean {
        return surroundingCoords(pos).all { grid[pos.second][pos.first] < grid[it.second][it.first] }
    }

    fun surroundingCoords(pos : Coord) : List<Coord> {
        return neighbours.map{ (a,b) ->  Coord(a+pos.first, b+pos.second)}
        .filter { withinBounds(it) }
    }
    
    fun findBasin(pos : Coord, basin: Set<Coord>) : Set<Coord> {
        val surrounding = surroundingCoords(pos)
            .filter { grid[it.second][it.first] != 9 }
            .filter { grid[it.second][it.first] > grid[pos.second][pos.first] }
            .filter { !basin.contains(it) }
            .toSet()
        return if (surrounding.isEmpty()) {
            surrounding
        } else {
            surrounding + surrounding.flatMap { findBasin(it, basin + surrounding + pos) } + pos
        }
    }

    fun withinBounds(pos: Coord) : Boolean = pos.first in 0 until width && pos.second >= 0 && pos.second < height
}

fun String.toGrid() : Grid {
    return this.lines().map { it.toCharArray().map { Integer.parseInt("$it") }.toTypedArray() }.toTypedArray()
}
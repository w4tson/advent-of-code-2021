package aoc.day09

import aoc.takeWhileInclusive

typealias Grid = Array<Array<Int>>
typealias Coord = Pair<Int, Int>

class HeatMap(val grid : Grid) {

    private var width : Int = grid[0].size
    private var height : Int = grid.size
    
    fun topThreeBasins() : Int {
        val res =  basins().sorted().take(3)
        return res.get(0) * res.get(1)* res.get(2)
    }
    
    fun basins() : List<Int> {
        return findLowPoints().map { surroundingBasinCoords(it).count() + 1 }
    }
            
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

        return listOf(
            Coord(0,-1),
            Coord(0,+1),
            Coord(-1,0),
            Coord(+1,0)
        ).map{ (a,b) ->  Coord(a+pos.first, b+pos.second)}
        .filter { withinBounds(it) }
    }

    fun surroundingBasinCoords(pos : Coord) : List<Coord> {

        return listOf(
            Coord(0,-1),
            Coord(0,+1),
            Coord(-1,0),
            Coord(+1,0)
        )
            .flatMap { lineOfSightSequence(pos, it)
                .takeWhileInclusive{ (a,b) -> withinBounds(Coord(a,b)) && grid[b][a] != 9 }.toList()
            }
            .filter(this::withinBounds)
    }

    fun withinBounds(pos: Coord) : Boolean = pos.first in 0 until width && pos.second >= 0 && pos.second < height

    fun display() {
        grid.forEach {
            it.forEach {
                print(it)
            }
            println()
        }
        println()
    }

    fun lineOfSightSequence(init: Coord, vector: Coord) : Sequence<Coord> {
        return generateSequence(Coord(init.first + vector.first, init.second + vector.second),
            { (x ,y) -> Coord(x + vector.first, y + vector.second) })
    }
}

fun String.toGrid() : Grid {
    return this.lines().map { it.toCharArray().map { Integer.parseInt("$it") }.toTypedArray() }.toTypedArray()
}



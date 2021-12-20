package aoc.day19

import aoc.chunkWhen
import aoc.day19.Rotation.*
import aoc.permutations

enum class Rotation(){ R0, R90, R180, R270}
data class Orientation(val x : Rotation, val y : Rotation, val z: Rotation)
data class Coord3(val x: Int, val y : Int, val z: Int)
operator fun Coord3.plus(other: Coord3) : Coord3 {
    return Coord3(x + other.x, y + other.y, z + other.z)
}

fun Coord3.rotateX() : Coord3 = Coord3(x, z, y)
fun Coord3.rotateY() : Coord3 = Coord3(z, y, x)
fun Coord3.rotateZ() : Coord3 = Coord3(y, x, x)

val ORIGIN = Coord3(0,0,0)

fun Coord3.reverse() : Coord3{
    return Coord3(x*-1,y*-1,z*-1)
}

class Day19 {
}

val allOrientations : List<Orientation> =
    values()
        .permutations(k = 3)
        .map { (x,y,z) -> Orientation(x,y,z) }
        .toList()


class Scanner(val id: Int, val beacons : Set<Coord3>, val orientation: Orientation = Orientation(R0, R0, R0), val origin:  Coord3 = ORIGIN) {
    override fun toString(): String {
        return "Scanner(id=$id, beacons=$beacons)"
    }
    
    fun reOrientOther(other : Scanner) : Scanner? {
        var result : Scanner? = null
        for (o in allOrientations) {
            val orientedOther = other.orient(o)
            for (b in beacons) {
                for (ob in orientedOther.beacons()) {
                    val potentialOrigin = b + ob.reverse()
                    val intersection = orientedOther.beaconsRelativeTo(potentialOrigin).intersect(beacons)
                    if (intersection.size >= 12) {
                        //here we could reset this to R0 and rewrite the coords. maintaining the data for now
                        result = Scanner(other.id, other.beacons, o, potentialOrigin)
                        break;
                    }
                }
            }
        }
        
        return result
    }

    private fun orient(configuration: Orientation): Scanner {
        return Scanner(id, beacons, configuration)
    }

    fun beaconsRelativeTo(origin: Coord3) : Set<Coord3> {
        return beacons().map { origin + it }.toSet()
    }
    
    fun beacons() : Set<Coord3> {
        //re orient based on the orientation
        
        return emptySet()
    }
    
    
} 

fun String.toScanners() : List<Scanner> = this.lines().chunkWhen { it.isEmpty() }.map { aoc.day19.toScanners(it) }

fun toScanners(input : List<String>) : Scanner {
    val title = input.first()
    val id = Integer.parseInt(title.replace("--- scanner ","").replace(" ---","").trim())
    val beacons = input.drop(1).map{ val (x,y,z) = it.split(",");  Coord3(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(z))}.toSet()
    return Scanner(id, beacons)
}
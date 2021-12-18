package aoc

import java.nio.charset.StandardCharsets
import java.util.*

class Util {

    companion object {
        fun readInput(fileName: String): String =
            this::class.java.getResource(fileName).readText(StandardCharsets.UTF_8)
    }
}

fun repeatRange(from : Int = 0, repeatAfter : Int) : Sequence<Int> {
    return generateSequence(from, { 
        if (it == repeatAfter) { from } else { it +1 }
    })
}



fun <T> List<T>.cycle() : Sequence<T> {
    return repeatRange(repeatAfter =  this.size).map { this[it] }
}

fun naturalNumbers() : Sequence<Int> = (0..Int.MAX_VALUE).asSequence()

fun <T> Sequence<T>.takeWhileInclusive(predicate : (T) -> Boolean) : Sequence<T> {
    var keepGoing: Boolean
    var prevPred = true
    return this.takeWhile{
        keepGoing = prevPred
        prevPred = predicate(it)
        keepGoing
    }
}

fun <T> List<T>.takeWhileInclusive(predicate : (T) -> Boolean) : List<T> {
    var keepGoing: Boolean
    var prevPred = true
    return this.takeWhile{
        keepGoing = prevPred
        prevPred = predicate(it)
        keepGoing
    }
}

fun Sequence<Long>.multiply() : Long = this.reduce { acc, i -> acc * i}
fun Iterable<Long>.multiply() : Long = this.reduce { acc, i -> acc * i}


fun <T> permutations(list : List<T>) : Sequence<List<T>> {
    return sequence {
        if (list.size <= 1) yield(list) else {
            list.indices.forEach { i ->
                permutations(list.slice(0 until i) + list.slice(i+1 until list.size)).forEach { p ->
                    yield(listOf(list[i]) +p)
                }
            }
        }
    }
}

fun String.charList() : List<Char> = this.toCharArray().toList()

fun <T> List<List<T>>.contentDeepEquals(other: List<List<T>>) : Boolean {
    this.indices.forEach {
        val a = this[it]
        val b = other[it]
        a.indices.forEach {
            if (a[it] == b[it]) return false
        }
    }
    return true
}

fun <T> List<T>.toDeque() :Deque<T> {
    val d = ArrayDeque<T>()
    this.forEach { d.addLast(it) }
    return d
}

fun String.toListOfInt(): List<Int> {
    return this.split(",").map { it.toInt() }
}

fun String.toListOfLong(): List<Long> {
    return this.split(",").map { it.toLong() }
}

data class Coord(val x: Int, val y : Int)
operator fun Coord.plus(other : Coord) : Coord {
    return Coord(this.x + other.x, this.y + other.y)
}

operator fun Coord.rangeTo(other : Coord) : Iterable<Coord> {
    return (this.y..other.y).flatMap { y ->
        (this.x..other.x).map { x -> Coord(x,y) }
    }
}


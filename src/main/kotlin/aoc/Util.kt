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


//https://gist.github.com/Tandrial/a63ccae712f08a56c2ca9a57b5e3be74
fun String.permutations(): Sequence<String> = toList().permutations().map { it.joinToString(separator = "") }

fun <T : Comparable<T>> Set<T>.permutations(): Sequence<List<T>> = toList().permutations()

fun <T : Comparable<T>> Array<T>.permutations(k: Int = this.count()): Sequence<List<T>> = toList().permutations(k)

/**
 * Return successive r length permutations of elements in the [Iterable].
 * If r is not specified, then r defaults to the length of the iterable and all possible
 * full-length permutations are generated. Permutations are emitted in lexicographic sort order.
 * So, if the input iterable is sorted, the permutation tuples will be produced in sorted order.
 * Elements are treated as unique based on their position, not on their value.
 * So if the input elements are unique, there will be no repeat values in each permutation.
 *
 *
 * @param k The length of the permutation
 *
 * @return [Sequence] of all k-length possible permutations
 */
fun <T : Comparable<T>> Iterable<T>.permutations(k: Int = this.count()): Sequence<List<T>> {
    val elements = this@permutations.toMutableList()
    val n = elements.count()
    return if (k == n) sequence {
        // https://en.wikipedia.org/wiki/Heap%27s_algorithm
        val indicies = 0.repeat(n).toMutableList()
        yield(elements)
        var i = 0
        while (i < n) {
            if (indicies[i] < i) {
                if (i.rem(2) == 0) elements.swapByIndex(0, i)
                else elements.swapByIndex(indicies[i], i)
                yield(elements)
                indicies[i] += 1
                i = 0
            } else {
                indicies[i] = 0
                i += 1
            }
        }
    } else {
        sequence {
            // https://alistairisrael.wordpress.com/2009/09/22/simple-efficient-pnk-algorithm/
            while (true) {
                yield(elements.take(k))
                var edge = k - 1
                // find j in (k…n-1) where a_j > a_edge
                var j = (k until n).firstOrNull { elements[it] > elements[edge] } ?: n
                if (j < n) {
                    elements.swapByIndex(edge, j)
                } else {
                    elements.reverseRightOf(edge)
                    // find rightmost ascent to left of edge
                    edge = (edge - 1 downTo 0).firstOrNull { elements[it] < elements[it + 1] } ?: break
                    // find j in (n-1 … i+1) where a_j > a_i
                    j = (j - 1 downTo edge).firstOrNull { elements[edge] < elements[it] } ?: edge
                    elements.swapByIndex(edge, j)
                    elements.reverseRightOf(edge)
                }
            }
        }
    }
}

fun <T : Any> MutableList<T>.swapByIndex(from: Int, to: Int) {
    this[from] = this[to].also { this[to] = this[from] }
}

fun <T : Any> MutableList<T>.reverseRightOf(start: Int) {
    val end = start + Math.floorDiv(size - start, 2)
    ((start + 1)..end).forEach { swapByIndex(it, size - it + start) }
}

/**
 * Make a [Sequence] that returns object over and over again.
 * Runs indefinitely unless the [times] argument is specified.
 *
 * @param times How often the object is repeated. null means its repeated indefinitely
 */
fun <T : Any> T.repeat(times: Int? = null): Sequence<T> = sequence {
    var count = 0
    while (times == null || count++ < times) yield(this@repeat)
}

fun <T> Iterable<T>.chunkWhen(condition: (T) -> Boolean): List<List<T>> = this.fold(mutableListOf(mutableListOf<T>())) { acc, t ->
    if (condition.invoke(t)) {
        acc.add(mutableListOf())
    } else {
        acc.last().add(t)
    }
    acc
}.filter {
    it.isNotEmpty()
}
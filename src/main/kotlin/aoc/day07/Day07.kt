package aoc.day07
import java.lang.Math.abs

typealias DistanceMeasure = (values : List<Int>, p : Int) -> Int

fun distanceMeasure(values : List<Int>, p : Int) : Int = values.map { abs(it - p) }.sum()
fun distanceMeasure2(values : List<Int>, p : Int) : Int = values.map { (1..abs(it - p)).sum() }.sum()

fun calc(input : List<Int>, dm : DistanceMeasure = ::distanceMeasure): Int {
    val sorted = input.sorted()

    return (sorted.first()..sorted.last())
        .map { Pair(it, dm(input, it)) }
        .minBy { it.second }?.second!!
}
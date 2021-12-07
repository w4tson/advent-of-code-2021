package aoc.day07

fun part1(input : List<Int>): Int {
    val sorted = input.sorted()

    return (sorted.first()..sorted.last())
        .map { Pair(it, distanceMeasure(input, it)) }
        .minBy { it.second }?.second!!
}

fun distanceMeasure(values : List<Int>, p : Int) : Int = values.map { distanceTo(it, p) }.sum()

fun distanceTo(x: Int, p : Int) : Int = Math.abs(x - p)

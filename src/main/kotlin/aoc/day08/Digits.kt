package aoc.day08

class Digits {
}

val UNIQUE_LENGTHS = listOf(2,4,3,7)

fun part1(input: String) : Int {
    return input.lines()
        .flatMap { it.split(" | ")[1].split(" ") }.also { println(it) }
        .map { it.length }
        .filter { UNIQUE_LENGTHS.contains(it) }
        .count()
}
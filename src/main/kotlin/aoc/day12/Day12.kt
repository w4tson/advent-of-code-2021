package aoc.day12

import java.util.*

typealias LegalPredicate = (path : List<String>) -> Boolean

class Day12(input : String) {
    private val graph : Map<String, List<String>> = input.toGraph()

    fun part1() : Set<String> {
        return findPaths(this::isLegalForPart1)
    }

    fun part2() : Set<String> {
        return findPaths(this::isLegalForPart2)
    }

    private fun findPaths(legalPredicate : LegalPredicate) : Set<String> {
        val paths = Stack<List<String>>()
        val result = mutableListOf<List<String>>()
        paths.push(listOf("start"))
        
        while (paths.isNotEmpty()) {
            val path = paths.pop()
            val node = path.last()

            val newPaths = graph.get(node)?.map {
                path + it
            }
            newPaths?.filter { legalPredicate(it) }
            ?.forEach { 
                if (it.last() == "end") result.add(it) else paths.push(it) 
            }
        }
        return result.map { it.joinToString(",") }.sorted().toSet()
    }
    
    private fun isLegalForPart1(path: List<String>) : Boolean {
        return !path.groupingBy { it }.eachCount().any { (k,v) ->  k[0].isLowerCase() && v > 1}
    }
    
    private fun isLegalForPart2(path: List<String>) : Boolean {
        val lowerCaseCounts = path.groupingBy { it }
            .eachCount()
            .filter { (k, _) ->  k[0].isLowerCase()}

        val endCount : Int = lowerCaseCounts.getOrDefault("end", 0)
        return lowerCaseCounts["start"] == 1 &&
                endCount < 2 &&
                lowerCaseCounts.values.filter { v -> v > 1 }
            .count() < 2 && lowerCaseCounts.values.all { v -> v < 3 }
    }
}

fun String.toGraph() : Map<String, List<String>> {
    return this.lines()
        .flatMap { val (a,b) = it.split("-"); listOf(Pair(a,b), Pair(b,a)) }
        .fold(mutableMapOf()) { acc, (a,b) ->
            acc.compute(a){ k,v -> v.orEmpty() + b}
            acc
        }
}





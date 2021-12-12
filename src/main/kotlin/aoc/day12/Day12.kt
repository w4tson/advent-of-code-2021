package aoc.day12

import java.util.*

class Day12(input : String) {
    val graph : Map<String, List<String>> = input.toGraph()
    
    
    fun part1() : Set<String> {
        println("Graph=$graph")
        val paths = Stack<List<String>>()
        val result = mutableListOf<List<String>>()
        paths.push(listOf("start"))
        
        while (paths.isNotEmpty()) {
//            println(paths)

            val path = paths.pop()
            val node = path.last()

            val newPaths = graph.get(node)?.map {
                path + it
            }
            newPaths?.filter { isLegal(it) }
            ?.forEach { 
                if (it.last() == "end") result.add(it) else paths.push(it) 
            }
        }
        return result.map { it.joinToString(",") }.sorted().toSet()
    }
    
    fun isLegal(path: List<String>) : Boolean {
        return !path.groupingBy { it }.eachCount().any { (k,v) ->  k[0].isLowerCase() && v > 1}
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





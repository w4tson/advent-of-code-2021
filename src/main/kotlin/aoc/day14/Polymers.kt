package aoc.day14


fun String.toPolymerTemplate() : String = this.lines()[0].trim()

fun String.toPolymerPairs() : Map<String,Char> {
    return this.lines().drop(2).map { val (from,to) = it.split(" -> "); Pair(from, to[0]) }.toMap()
}

fun String.toPolymerPairsTree() : Map<String, List<String>> {
    return this.lines().drop(2).map { 
        val (from,to) = it.split(" -> ")
        val left = from[0]
        val right = from[1]
        val middle = to[0]
        Pair(from, listOf("$left$middle", "$middle$right")) }.toMap()
}

class Polymers(val input : String) {
    val template = input.toPolymerTemplate()
    val pairs = input.toPolymerPairs()
    val pairsTree = input.toPolymerPairsTree()

    fun polymerSequence() : Sequence<String> {
        return generateSequence(template) { 
            it.windowed(2).map { "${it[0]}${pairs[it]}" }.joinToString("") + it.last() 
        }
    }

    fun polymerSequence2() : Sequence<Map<String, Long>> {
        val seed : Map<String, Long> = template.windowed(2)
            .groupingBy { it }
            .eachCount()
            .mapValues { it.value.toLong() }
            .toMap()
        
        return generateSequence(seed) { prev ->
            prev.keys.flatMap { pair ->
                pairsTree[pair]!!.map { Pair(it, prev.getOrDefault(pair,1)) }
            }.fold(emptyMap()) { acc, item ->
                val newAcc = acc.toMutableMap()
                newAcc.compute(item.first) { k, v -> if (v == null) item.second else v + item.second}
                newAcc
            }
        }
    }
    
    fun part2(iterations : Int) : Long {
        val res = polymerSequence2().drop(iterations).first().map { Pair(it.key[1], it.value) }
            .fold(mutableMapOf<Char, Long>()) { acc, item ->
                acc.compute(item.first) { k, v ->
                    if (v == null) {
                        item.second
                    } else {
                        v + item.second
                    }
                }
                acc
            }
        
        //always bump the first letter by 1
        res.computeIfPresent(input[0]) { k,v -> v+1}
        
        val max = res.values.max()!!
        val min = res.values.min()!!
        return max - min
    }
    
    fun part1() : Int {
        val last = polymerSequence().take(11).last()

        val counts = last.groupingBy { it }.eachCount()
        val max = counts.values.max()!!
        val min = counts.values.min()!!
        return max - min
    }
    
    fun <T> inc(k: T, v: Long?) :Long?  { return if (v == null) 1L else v + 1L }

}



package aoc.day06

fun fishSimulSeq(init: List<Long>) : Sequence<List<Long>> {
    return generateSequence(init) { prev ->
        val newFish = prev.filter { it == 0L }.map { 8L }
        prev.map {
            when(it) {
                0L -> 6
                else -> it -1
                
            }
        } + newFish
    }
}


fun fishSimulSeq2(init: List<Long>) : Sequence<Map<Long, Long>> {
    val base : Map<Long, Long> = init.groupingBy { it }.eachCount().mapValues { it.value.toLong() }
    return generateSequence(base) { prev ->
        val previousZeros = prev.getOrDefault(0, 0)
        
        mapOf(
            0L to prev.getOrDefault(1L, 0L) ,
            1L to prev.getOrDefault(2L, 0L) ,
            2L to prev.getOrDefault(3L, 0L) ,
            3L to prev.getOrDefault(4L, 0L) ,
            4L to prev.getOrDefault(5L, 0L) ,
            5L to prev.getOrDefault(6L, 0L) ,
            6L to prev.getOrDefault(7L, 0L) + previousZeros,
            7L to prev.getOrDefault(8L, 0L) ,
            8L to previousZeros
        )
    }
}

fun part2(input : List<Long>) =fishSimulSeq2(input).take(257).last().values.sum()
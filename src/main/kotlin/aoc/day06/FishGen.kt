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

fun part2(init: List<Long>) {
    
//    init.map { Fish(0, it) }[0]
}

val ref = fishSimulSeq(listOf(1L)).take(9).mapIndexed { i, it -> Pair(i, it) }.toMap()


fun foo(day: Int) {
    val index = day % 7
    val base = ref[index]
    val middle = ref[index-1]
}

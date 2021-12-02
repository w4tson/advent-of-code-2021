package aoc.day02

class Submarine() {
    
    fun move(moves: String) : Int {
        val position = moves.lines().fold(Pair(0, 0)) { (horiz, depth), instr ->
            val (direction, value) = instr.split(" ")
            val delta = value.toInt()
            when(direction) {
                "forward" -> Pair(horiz + delta, depth)
                "up" -> Pair(horiz, depth - delta)
                "down" -> Pair(horiz, depth + delta)
                else -> throw RuntimeException("Nope")
            }    
        }
        
        return position.first * position.second
    }

    fun move2(moves: String) : Int {
        val position = moves.lines().fold(Triple(0, 0, 0)) { (horiz, depth, aim), instr ->
            val (direction, value) = instr.split(" ")
            val delta = value.toInt()
            when(direction) {
                "down" -> Triple(horiz, depth, aim+ delta)
                "up" -> Triple(horiz, depth, aim -delta)
                "forward" -> Triple(horiz+delta, depth+ (aim*delta), aim)
                else -> throw RuntimeException("Nope")
            }
        }

        return position.first * position.second
    }
}
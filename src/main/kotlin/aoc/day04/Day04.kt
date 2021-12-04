package aoc.day04

data class BingoNumber(val num : Int, var called : Boolean)

class Board(val board : List<List<BingoNumber>>) {
    
    fun bingo() : Boolean {
        val rows = board.map { it.count { it.called } }.contains(5)
        val cols = (0..4).map { i -> board.map { it[i] } }.map { it.count { it.called } }.contains(5)
        return rows || cols
    }
    
    fun sumOfUnmarked() : Int {
        return board.sumBy { it.filter { !it.called }.sumBy { it.num } }
    }
    
    fun display() {
        return board.forEach {
            it.forEach  { 
                if (it.called) {
                    print("  *")
                } else {
                    print("${it.num}".padStart(3))}; 
                }
                println() 
        }
    }
}

class BingoSubSystem(var boards: List<Board>, val nums : List<Int>) {
    var winners = mutableListOf<Pair<Board, Int>>()
    
    fun call(n : Int) {
        boards.forEach { board ->
            board.board.forEach{ 
                it.forEach{ if (n == it.num) it.called=true}
            }
            
            if (board.bingo() && !winners.any { board == it.first }) {
                val score = board.sumOfUnmarked() * n
                winners.add(Pair(board, score))
            }
        }
    }
    
    fun findFirstWinner() {
        nums.forEach { call(it) }
        println(winners.first().second)
    }
    
    fun findLastWinner() {
        nums.forEach { call(it) }
        println(winners.last().second)
    }
}


fun String.toBingoSubystem() : BingoSubSystem {
    val lines = this.lines()
    val nums = lines[0].toListOfInt()
    val boards : List<Board> = lines.drop(1).windowed(6, 6)
        .map{ chunkOf6Lines -> chunkOf6Lines.drop(1).map { line ->
            line.windowed(2, 3).map { BingoNumber(Integer.parseInt(it.trim()), false) }
        }}
        .map { Board(it) }
    return BingoSubSystem(boards, nums)
}


fun String.toListOfInt(): List<Int> {
    return this.split(",").map { it.toInt() }
}

package aoc.day04

import aoc.takeWhileInclusive

data class BingoNumber(val num : Int, var called : Boolean)

class Board(val board : List<List<BingoNumber>>){
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
    
    fun call(n : Int) : Int? {
        boards.forEach { 
            it.board.forEach{ 
                it.forEach{ if (n == it.num) it.called=true}
            }
            
            if (it.bingo()){
                it.display()
                val sumOfUnmarked = it.sumOfUnmarked()
                val score = sumOfUnmarked * n
                println("BINGO! n=$n  sumofunmarked=${sumOfUnmarked} score=${score}")
                return score
            }
            
        }
        
        return null
    }
    
    fun callAll() {
        val last = nums.takeWhile { this.call(it) == null }
        println(last)
    }
    
}

class Day04 {
    
    
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
//each window is a list of string
package aoc.day03

import aoc.charList

class Diagnostics(val input: String) {
    
    fun powerConsumption() : Int {
        val gammaString = calculateGammaString(input)
        val epsilon = calculateEpsilon(gammaString)
        return Integer.parseInt(gammaString, 2) * epsilon
    }

    fun calculateGammaString(input: String): String {
        val readings = input.lines()

        val gamma = (0..readings[0].length - 1)
            .map { mostCommonBit(columnAt(readings, it)) }
            .joinToString("")
        return gamma
    }
    
    fun calculateEpsilon(input: String) : Int {
        return Integer.parseInt(input.replace('1', 'x').replace('0','1').replace('x','0'), 2)
    }
     
    fun columnAt(input: List<String>, index: Int) : String {
        return input.map { it[index] }.joinToString("")
    }
    
    fun mostCommonBit(binaryString : String) : Char {
        return if (binaryString.charList().filter { it == '1' }.count() > (binaryString.length / 2)) '1' else '0'
    }
}
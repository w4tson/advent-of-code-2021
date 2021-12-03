package aoc.day03

import aoc.charList

data class State(val index : Int, val inputs: List<String>)

class Diagnostics(val input: String) {
    private val diagnosticReport = input.lines()
    
    fun lifeSupportRating() : Int {
        return oxygenGeneratorRating() * co2ScrubberRating()
    }
    
    fun oxygenGeneratorRating() : Int {
        return calcRating('1',::mostCommonBit)
    }
    
    fun co2ScrubberRating() : Int {
        return calcRating('0',::leastCommonBit)
    }
    
    private fun calcRating(default : Char, bitCriteria : (String, Char) -> Char) : Int {
        val res = diagnosticReport.indices.fold(State(0, diagnosticReport)) { state, _ ->
            if (state.inputs.size > 1) {
                val column = columnAt(state.inputs, state.index)
                val bit = bitCriteria(column, default)
                val filteredInputs = state.inputs.filter { it[state.index] == bit }
                State(state.index + 1, filteredInputs)
            } else {
                state
            }
        }
        
        return res.inputs[0].parseBinaryInt()
    }
    
    fun powerConsumption() : Int {
        val gammaString = calculateGammaString()
        val epsilon = calculateEpsilon(gammaString)
        return gammaString.parseBinaryInt() * epsilon
    }

    fun calculateGammaString(): String {
        return (diagnosticReport[0].indices)
            .map { mostCommonBit(columnAt(diagnosticReport, it)) }
            .joinToString("")
    }
    
    fun calculateEpsilon(input: String) : Int {
        return input.replace('1', 'x')
                .replace('0','1')
                .replace('x','0')
                .parseBinaryInt()
    }
     
    fun columnAt(input: List<String>, index: Int) : String {
        return input.map { it[index] }.joinToString("")
    }
}

fun mostCommonBit(binaryString : String, default : Char = '0') : Char {
    val counts = binaryString.charList().groupingBy { it  }.eachCount()
    return if (counts['1'] == counts['0']) {
        default
    } else {
        counts.maxBy { it.value }!!.key
    }
}

fun leastCommonBit(binaryString: String, default: Char = '0') : Char {
    val counts = binaryString.charList().groupingBy { it  }.eachCount()
    return if (counts['1'] == counts['0']) {
        default
    } else {
        counts.minBy { it.value }!!.key
    }
}
    
fun String.parseBinaryInt() : Int {
    return Integer.parseInt(this, 2)
}

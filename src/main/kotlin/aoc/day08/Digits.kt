package aoc.day08
//    aaaa    
//   b    c  
//   b    c  
//    dddd    
//   e    f  
//   e    f  
//    gggg


val zero = Digit(0,"abcefg")
val one = Digit(1,"cf")
val two = Digit(2,"acdeg")
val three = Digit(3,"acdfg")
val four = Digit(4,"bcdf")
val five = Digit(5,"abdfg")
val six = Digit(6,"abefg")
val seven = Digit(7,"acf")
val eight = Digit(8,"abcefg")
val nine = Digit(9,"abcdf")

val digits = listOf(zero, one, two, three, four, five, six, seven, eight, nine)

val UNIQUE_LENGTHS = listOf(2,4,3,7)

fun part1(input: String) : Int = findUnqiueDigits(input).count()

fun findDigits(input: String) : List<String> {
    return input.lines()
        .flatMap { it.split(" | ")[1].split(" ") }
}

fun findUnqiueDigits(input: String) : List<String> {
    return input.lines()
        .flatMap { it.split(" | ")[1].split(" ") }
        .filter { UNIQUE_LENGTHS.contains(it.length) }
}

//letter -> cnofig

//d
//e

class Digit(val value: Int, val segment: String, var possibles: String = "abcdefg") {

    fun commonSegments(other : Digit) :String = segment.filter { other.segment.contains(it) }
    fun commonPossibles(other : Digit) :String = possibles.filter { other.possibles.contains(it) }
    
    fun exclusiveSegments(other : Digit) : String = segment.filter {  !other.segment.contains(it) }
    fun exclusivePossibles(other : Digit) : String = possibles.filter {  !other.possibles.contains(it) }
}
    
fun part2(input: String) {
    findUnqiueDigits(input).find { it.length == 2 }?.let { digits[1].possibles = it}
    findUnqiueDigits(input).find { it.length == 4 }?.let { digits[4].possibles = it}
    findUnqiueDigits(input).find { it.length == 3 }?.let { digits[7].possibles = it}
    findUnqiueDigits(input).find { it.length == 7 }?.let { digits[8].possibles = it}

//    val commonSegments = digits[7].commonSegments(digits[1])
    val exclusiveSeg = digits[7].exclusiveSegments(digits[1])
    val exclusive = digits[7].exclusivePossibles(digits[1])
    mapOf("a" to exclusive)

    val exclusive = digits[7].exclusivePossibles(digits[1])
}
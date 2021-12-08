package aoc.day08

fun part1(input: String) : Int{
    return input.lines()
        .flatMap { it.split(" | ")[1].split(" ") }
        .count { listOf(2,3,4,7).contains(it.length) }
}


fun getDigitPatterns(input: String) : List<String> {
    return input.lines()
        .flatMap { it.split(" | ")[0].split(" ") }
}

fun getDigits(input: String) : List<String> {
    return input.lines()
        .flatMap { it.split(" | ")[1].split(" ") }
}

fun countDigit(input : String) :Int {
    val digits = getDigitPatterns(input)
    val one = digits.find { it.length == 2 }!!.toSet()
    val seven = digits.find { it.length == 3 }!!.toSet()
    val four = digits.find { it.length == 4 }!!.toSet()
    val eight = digits.find { it.length == 7 }!!.toSet()

    val nine = digits.filter { it.length == 6 }
        .find { it.toSet().minus(four.plus(seven)).size == 1 }
        ?.toSet()!!
    
    val zero = digits.filter { it.length == 6 }
        .find { seven.intersect(it.toSet()).size == 3 && !nine.equals(it.toSet()) }
        ?.toSet()!!
    
    val six = digits.find { it.length == 6 && !nine.equals(it.toSet()) && !zero.equals(it.toSet()) }
        ?.toSet()!!
    
    val five = digits.find { it.length == 5 && six.minus(it.toSet()).size == 1}?.toSet()!!
    
    val three = digits.find { it.length == 5 && five.minus(it.toSet()).size == 1}?.toSet()!!
    
    val two = digits.find { it.length == 5 && five.minus(it.toSet()).size == 2}?.toSet()!!
    
    val nums = mapOf(
        zero to 0,
        one to 1,
        two to 2,
        three to 3,
        four to 4,
        five to 5,
        six to 6,
        seven to 7,
        eight to 8,
        nine to 9
    )

    val digits1 = getDigits(input).map { nums.get(it.toSet()) }.joinToString("")
    return Integer.parseInt(digits1)
}
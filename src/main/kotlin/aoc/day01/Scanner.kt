package aoc.day01

class Scanner(private val depths : List<Int>) {
    
    private fun scanValues(nums : List<Int>) : Int {
        return nums.zipWithNext().count { it.first < it.second }
    }
    
    fun count() : Int {
        return scanValues(depths)
    }

    fun part2() : Int {
        return scanValues(depths.windowed(3,1).map { it.sum() })
    }
}
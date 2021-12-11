package aoc.day10

import java.util.*

val bracketMatches = mapOf(
    '(' to ')',
    '{' to '}',
    '[' to ']',
    '<' to '>'
)

val corruptionScores = mapOf(
    ')' to 3,
    ']' to 57,
    '}' to 1197,
    '>' to 25137
)

open class Chunk(val char: Char, open var children : MutableList<Chunk>) 

class RoundBracket(override var children : MutableList<Chunk> = mutableListOf()): Chunk('(', children)
class SquareBracket(override var children : MutableList<Chunk> = mutableListOf()): Chunk('[', children)
class CurlyBracket(override var children : MutableList<Chunk> = mutableListOf()): Chunk('{', children)
class PointyBracket(override var children : MutableList<Chunk> = mutableListOf()): Chunk('<', children)

fun corruptionScore(input : String) : Int = input.lines()
    .map { isCorrupt(it)}
    .filter { it != null }
    .map { corruptionScores.getOrDefault(it!!, 0) }
    .sum()

fun isCorrupt(s : String) : Char? {
    return toChunks(s).fold(onFailure = {
        (it as CorruptionException).c
    },
    onSuccess = {null})
}

fun toChunks(s : String) : Result<Chunk> {
    val stack = Stack<Chunk>()
    val bracketStack = Stack<Char>()
    var result : Chunk? = null
    
    s.forEach { 
        if (isOpeningBracket(it)) {
            val newChunk = toChunk(it)
            stack.push(newChunk)
            bracketStack.push(it)
            if (result == null) result = newChunk
        } else if (bracketStack.isNotEmpty() && bracketMatches[bracketStack.peek()] == it){
            bracketStack.pop()
            val chunk = stack.pop()
            if (!stack.isEmpty()) {
                val peek = stack.peek()
                peek.children.add(chunk)
            }
        } else if (bracketStack.isNotEmpty() && isClosingBracket(it) && bracketMatches[bracketStack.peek()] != it) {
            return Result.failure(CorruptionException(it))
        }
    }

    return Result.success(result!!)
    
}

fun isOpeningBracket(char : Char) = bracketMatches.keys.contains(char)
fun isClosingBracket(char : Char) = bracketMatches.values.contains(char)


fun toChunk(c : Char) : Chunk {
    return when(c) {
        '(' -> RoundBracket()
        '[' -> SquareBracket()
        '{' -> CurlyBracket()
        '<' -> PointyBracket()
        else -> error("Nope")
    }
}
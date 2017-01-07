package d3

import utils.Day
import utils.window

fun main(args: Array<String>) = Day3()

val REGEX_LINE = "\\s+".toRegex()

object Day3: Day({
    +res("3.txt")
}) {
    override fun solve(input: List<String>, part1: Boolean): Any {
        var triangles: List<List<Int>> = input
                .map { it.split(REGEX_LINE, 0).mapNotNull(String::toIntOrNull) }

        if (!part1) {
            triangles = triangles
                    .window(size = 3, step = 3)
                    .flatMap { threeLines ->
                        (0..2).map { y -> (0..2).map { x -> threeLines[x][y] } }.asSequence()
                    }
                    .toList()
        }

        val count = triangles
                .count { sides ->
                    (0..2).all { isTriangle(sides, it) }
                }

        return count
    }

    fun isTriangle(sides: List<Int>, i: Int): Boolean {
        return sides[i] + sides[(i + 1) % 3] > sides[(i + 2) % 3]
    }
}

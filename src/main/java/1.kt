package d1

import utils.Day

val input = "R4, R4, L1, R3, L5, R2, R5, R1, L4, R3, L5, R2, L3, L4, L3, R1, R5, R1, L3, L1, R3, L1, R2, R2, L2, R5, L3, L4, R4, R4, R2, L4, L1, R5, L1, L4, R4, L1, R1, L2, R5, L2, L3, R2, R1, L194, R2, L4, R49, R1, R3, L5, L4, L1, R4, R2, R1, L5, R3, L5, L4, R4, R4, L2, L3, R78, L5, R4, R191, R4, R3, R1, L2, R1, R3, L1, R3, R4, R2, L2, R1, R4, L5, R2, L2, L4, L2, R1, R2, L3, R5, R2, L3, L3, R3, L1, L1, R5, L4, L4, L2, R5, R1, R4, L3, L5, L4, R5, L4, R5, R4, L3, L2, L5, R4, R3, L3, R1, L5, R5, R1, L3, R2, L5, R5, L3, R1, R4, L5, R4, R2, R3, L4, L5, R3, R4, L5, L5, R4, L4, L4, R1, R5, R3, L1, L4, L3, L4, R1, L5, L1, R2, R2, R4, R4, L5, R4, R1, L1, L1, L3, L5, L2, R4, L3, L5, L4, L1, R3"

fun main(args: Array<String>) = Day1()

object Day1 : Day({
    +("test input" to "R8, R4, R4, R8")
    +::input
}) {
    override fun String.split() = split(", ")

    enum class Dir(val dx: Int, val dy: Int) {
        N(0, 1), E(1, 0), S(0, -1), W(-1, 0)
        ;

        fun r() = values()[Math.floorMod(values().indexOf(this) + 1, 4)]
        fun l() = values()[Math.floorMod(values().indexOf(this) - 1, 4)]
    }

    override fun solve(input: List<String>, part1: Boolean): Any {
        var dir = Dir.N
        var x = 0
        var y = 0

        val visited = mutableSetOf<Pair<Int, Int>>()

        input
                .map { it[0] to it.substring(1).toInt() }
                .forEach { (d, n) ->
                    dir = if (d == 'R') dir.r() else dir.l()

                    repeat(n) {
                        x += dir.dx
                        y += dir.dy

                        if (!part1 && !visited.add(x to y)) {
                            return distance(x, y)
                        }
                    }
                }

        return distance(x, y)
    }

    private fun distance(x: Int, y: Int) = Math.abs(x) + Math.abs(y)
}

package d1

val input = "R4, R4, L1, R3, L5, R2, R5, R1, L4, R3, L5, R2, L3, L4, L3, R1, R5, R1, L3, L1, R3, L1, R2, R2, L2, R5, L3, L4, R4, R4, R2, L4, L1, R5, L1, L4, R4, L1, R1, L2, R5, L2, L3, R2, R1, L194, R2, L4, R49, R1, R3, L5, L4, L1, R4, R2, R1, L5, R3, L5, L4, R4, R4, L2, L3, R78, L5, R4, R191, R4, R3, R1, L2, R1, R3, L1, R3, R4, R2, L2, R1, R4, L5, R2, L2, L4, L2, R1, R2, L3, R5, R2, L3, L3, R3, L1, L1, R5, L4, L4, L2, R5, R1, R4, L3, L5, L4, R5, L4, R5, R4, L3, L2, L5, R4, R3, L3, R1, L5, R5, R1, L3, R2, L5, R5, L3, R1, R4, L5, R4, R2, R3, L4, L5, R3, R4, L5, L5, R4, L4, L4, R1, R5, R3, L1, L4, L3, L4, R1, L5, L1, R2, R2, R4, R4, L5, R4, R1, L1, L1, L3, L5, L2, R4, L3, L5, L4, L1, R3"
//val input = "R8, R4, R4, R8"

enum class Dir(val x: Int, val y: Int) {
    N(0, 1), E(1, 0), S(0, -1), W(-1, 0)
    ;

    companion object {
        val vals = values()
    }

    fun r() = vals[Math.floorMod(vals.indexOf(this) + 1, 4)]
    fun l() = vals[Math.floorMod(vals.indexOf(this) - 1, 4)]
}

var dir = Dir.N
var x = 0
var y = 0

val visited = mutableSetOf<Pair<Int, Int>>()

fun main(args: Array<String>) {
    input.split(", ")
            .map { it[0] to it.substring(1).toInt() }
            .forEach { (d, n) ->
                dir = if (d == 'R') dir.r() else dir.l()

                repeat(n) {
                    x += dir.x
                    y += dir.y

                    if (!visited.add(x to y)) {
                        println("HQ is at $x, $y, distance is ${Math.abs(x) + Math.abs(y)}")
                        return
                    }
                }
            }

    println(Math.abs(x) + Math.abs(y))
}
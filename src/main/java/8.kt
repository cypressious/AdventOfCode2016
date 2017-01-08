package d8

import utils.Day

fun main(args: Array<String>) = Day8()

object Day8 : Day({
    +"""rect 3x2
rotate column x=1 by 1
rotate row y=0 by 4
rotate column x=1 by 1"""

    +res("8.txt")
}) {

    const val WIDTH = 50
    const val HEIGHT = 6

    operator fun Array<Array<Boolean>>.get(x: Int, y: Int) = this[x][y]

    fun Array<Array<Boolean>>.print() = buildString {
        for (y in 0 until HEIGHT) {
            for (x in 0 until WIDTH) {
                append(if (get(x, y)) "#" else ".")
            }
            append("\n")
        }
    }

    fun Array<Array<Boolean>>.transform(op: Op) = op.transform(this)

    interface Op {
        fun transform(screen: Array<Array<Boolean>>): Array<Array<Boolean>>
    }

    class Rect(val x: Int, val y: Int) : Op {
        override fun transform(screen: Array<Array<Boolean>>) = Array(WIDTH) { x ->
            screen[x].mapIndexed { y, value ->
                if (x < this.x && y < this.y) true else value
            }.toTypedArray()
        }
    }

    class Column(val x: Int, val value: Int) : Op {
        override fun transform(screen: Array<Array<Boolean>>) = screen.mapIndexed { x, column ->
            if (x == this.x) {
                Array<Boolean>(HEIGHT) { y -> column[Math.floorMod(y - value, HEIGHT)] }
            } else {
                column
            }
        }.toTypedArray()
    }

    class Row(val y: Int, val value: Int) : Op {
        override fun transform(screen: Array<Array<Boolean>>) = screen.mapIndexed { x, _ ->
            Array<Boolean>(HEIGHT) { y ->
                if (y == this.y) {
                    screen[Math.floorMod(x - value, WIDTH), y]
                } else {
                    screen[x, y]
                }
            }
        }.toTypedArray()
    }

    val REGEX_RECT = """rect (\d+)x(\d+)""".toRegex()
    val REGEX_ROTATE = """rotate (column|row) [xy]=(\d+) by (\d+)""".toRegex()

    fun String.parseOp() = when (takeWhile { it != ' ' }) {
        "rect" -> {
            val match = REGEX_RECT.matchEntire(this)!!
            Rect(match.groupValues[1].toInt(), match.groupValues[2].toInt())
        }
        "rotate" -> {
            val match = REGEX_ROTATE.matchEntire(this)!!
            val type = match.groupValues[1]
            val coord = match.groupValues[2].toInt()
            val value = match.groupValues[3].toInt()

            if (type == "column") Column(coord, value) else Row(coord, value)
        }
        else -> throw IllegalArgumentException(this)
    }

    override fun solve(input: List<String>, part1: Boolean): Any? {
        val screen = Array(WIDTH) { Array<Boolean>(HEIGHT) { false } }

        val transformed = input.fold(screen) { screen, line -> screen.transform(line.parseOp()) }
        println(transformed.print())

        return transformed.sumBy { it.count { it } }
    }
}
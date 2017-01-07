package d6

import utils.Day

fun main(args: Array<String>) = Day6()

object Day6 : Day({
    +::testInput
    +res("6.txt")
}) {
    override fun solve(input: List<String>, part1: Boolean): Any? {
        val frequencies = Array(input.first().length) { LongArray(26) }

        for (line in input) {
            line.forEachIndexed { i, char ->
                val position = frequencies[i]
                val index = char - 'a'
                position[index] = position[index] + 1
            }
        }

        return frequencies.joinToString("") {
            val offset = if (part1) it.indexOfMax() else it.indexOfMin()
            ('a' + offset).toString()
        }
    }

    fun LongArray.indexOfMax() = withIndex()
            .maxBy { it.value }!!
            .index

    fun LongArray.indexOfMin() = withIndex()
            .filter { it.value != 0L }
            .minBy { it.value }!!
            .index

}


val testInput = """eedadn
drvtee
eandsr
raavrd
atevrs
tsrnev
sdttsa
rasrtv
nssdts
ntnada
svetve
tesnvt
vntsnd
vrdear
dvrsen
enarar"""

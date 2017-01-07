package d7

import utils.Day

fun main(args: Array<String>) = Day7()

object Day7 : Day({
    +::testInput
    +::testInput2
    +res("7.txt")
}) {
    override fun solve(input: List<String>, part1: Boolean): Any? {
        return input.count { it.solve(part1) }
    }

    fun String.solve(part1: Boolean): Boolean {
        var inBrackets = false
        var block = if (part1) "...." else "..."
        var hasAbba = false
        val abas = mutableSetOf<Pair<Char, Char>>()
        val babs = mutableSetOf<Pair<Char, Char>>()


        for (c in this) {
            when (c) {
                '[' -> inBrackets = true
                ']' -> inBrackets = false
            }

            block = block.substring(1) + c

            if (part1 && block.isAbba()) {
                if (inBrackets) {
                    return false
                } else {
                    hasAbba = true
                }
                continue
            }

            if (!part1 && block.isAba()) {
                if (!inBrackets) {
                    abas += block[0] to block[1]
                } else {
                    babs += block[1] to block[0]
                }
            }
        }

        if (part1) {
            return hasAbba
        } else {
            return abas.intersect(babs).isNotEmpty()
        }
    }

    fun String.isAba() = none { it == '[' || it == ']' } &&
            this[0] != this[1] &&
            this[0] == this[2]

    fun String.isAbba() = none { it == '[' || it == ']' } &&
            this[0] != this[1] &&
            this[0] == this[3] &&
            this[1] == this[2]
}


val testInput = """abba[mnop]qrst
abcd[bddb]xyyx
aaaa[qwer]tyui
ioxxoj[asdfgh]zxcvbn
"""

val testInput2 = """aba[bab]xyz
xyx[xyx]xyx
aaa[kek]eke
zazbz[bzb]cdb
"""

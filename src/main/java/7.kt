package d7

import utils.readResource
import utils.solveAll
import utils.splitLines

fun main(args: Array<String>) {
    solve(testInput, true)
    solve(testInput2, false)
    solveAll(::solve, readResource("7.txt"))
}

fun solve(input: String, part1: Boolean) {
    val count = input.splitLines().count { it.supportsAbba(part1) }
    println(count)
}

private fun String.supportsAbba(part1: Boolean): Boolean {
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


private fun String.isAba() = none { it == '[' || it == ']' } &&
        this[0] != this[1] &&
        this[0] == this[2]

private fun String.isAbba() = none { it == '[' || it == ']' } &&
        this[0] != this[1] &&
        this[0] == this[3] &&
        this[1] == this[2]

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

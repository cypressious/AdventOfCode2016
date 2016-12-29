package d5

import utils.md5
import utils.solveAll
import java.security.MessageDigest

fun main(args: Array<String>) {
    solveAll(::solve, testInput, input)
}

fun solve(input: String, part1: Boolean) {
    val md = MessageDigest.getInstance("MD5")
    val pw = Array(8) { "_" }
    var i = 0L
    var p = 0

    while (pw.any { it == "_" }) {
        val md5 = md.md5(input + i)
        if (md5.startsWith("00000")) {
            if (part1) {
                pw[p++] = md5.substring(5, 6)
            } else {
                val position = md5.substring(5, 6).toIntOrNull()
                if (position != null && pw.elementAtOrNull(position) == "_") {
                    pw[position] = md5.substring(6, 7)
                }
            }
        }
        i++
    }
    println(pw.joinToString(""))
}

val input = "ffykfhsq"
val testInput = "abc"
package d5

import utils.Day
import utils.md5
import java.security.MessageDigest

fun main(args: Array<String>) = Day5()

object Day5 : Day({
    +::testInput
    +::input
}) {
    override fun solve(input: String, part1: Boolean): Any? {
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

        return pw.joinToString("")
    }
}

val input = "ffykfhsq"
val testInput = "abc"
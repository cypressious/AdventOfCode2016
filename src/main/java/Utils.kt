package utils

import java.security.MessageDigest
import kotlin.reflect.KCallable

abstract class Day(inputs: Day.() -> Unit) {
    val inputs = mutableListOf<Pair<String?, String>>()

    init {
        inputs()
    }

    operator fun String.unaryPlus() = +(null to this)

    @Suppress("UNCHECKED_CAST")
    operator fun KCallable<String>.unaryPlus() = +(name to (this as () -> String).invoke())

    operator fun Pair<String?, String>.unaryPlus() = inputs.add(this)

    fun res(name: String) = name to readResource(name)

    operator fun invoke() {
        inputs.forEach {
            val name = it.first ?: it.second.take(10).takeWhile { it != '\n' } + "..."
            println("""Input: "$name"""")
            println("Part 1:")
            println(solve(it.second, true))
            println("Part 2:")
            println(solve(it.second, false))
            println()
        }
    }

    open fun String.split(): List<String> = splitLines()

    open fun solve(input: String, part1: Boolean) = solve(input.split(), part1)

    open fun solve(input: List<String>, part1: Boolean): Any? = ""
}


private class Foo

fun readResource(name: String) = Foo::class.java
        .getResourceAsStream("/" + name)
        .bufferedReader()
        .use { it.readText() }

fun String.splitLines() = split("\\r?\\n".toRegex())

fun <T> List<T>.window(size: Int, step: Int, wrap: Boolean = false): Sequence<List<T>> {
    require(size > 0)
    var i = 0

    return generateSequence {
        if (i >= this.size) return@generateSequence null

        val end = i + size
        val sub = subList(i, end.coerceAtMost(this.size)).apply { i += step }

        if (wrap && end >= this.size) {
            sub + subList(0, end % this.size)
        } else {
            sub
        }
    }
}

//http://stackoverflow.com/a/6565597/615306
fun MessageDigest.md5(s: String): String {
    val array = digest(s.toByteArray())
    val sb = StringBuffer()
    for (i in array.indices) {
        sb.append(Integer.toHexString(array[i].toInt() and 0xFF or 0x100).substring(1, 3))
    }
    return sb.toString()
}


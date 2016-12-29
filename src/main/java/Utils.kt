package utils

import java.security.MessageDigest

private class Foo

fun readResource(name: String) = Foo::class.java
        .getResourceAsStream("/" + name)
        .bufferedReader()
        .use { it.readText() }

inline fun solveAll(f: (String, Boolean) -> Any, vararg inputs: String) {
    inputs.forEach {
        println("Part 1")
        f(it, true)
        println("Part 2")
        f(it, false)
    }
}

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

fun String.splitLines() = split("\n")